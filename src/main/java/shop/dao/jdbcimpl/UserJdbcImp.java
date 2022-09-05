package shop.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import shop.dao.UserDao;
import shop.exeption.DataProcessingException;
import shop.lib.Dao;
import shop.model.Role;
import shop.model.User;
import shop.util.ConnectionUtil;

@Dao
public class UserJdbcImp implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User userFromResult = getUserFromResult(resultSet);
                userFromResult.setRoles(getRolesUser(userFromResult.getId()));
                return Optional.of(userFromResult);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Cant find  user by login");
        }

    }

    @Override
    public User create(User user) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO users (login, name, password, salt) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setBytes(4, user.getSalt());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            setRolesUser(user);
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Cant create user ");
        }
    }

    @Override
    public Optional<User> get(Long userId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User userFromResult = getUserFromResult(resultSet);
                userFromResult.setRoles(getRolesUser(userFromResult.getId()));
                return Optional.of(userFromResult);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user ");
        }
    }

    @Override
    public User update(User user) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE users SET login = ?, name = ?, password = ?, salt = ? "
                    + "WHERE user_id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setBytes(4, user.getSalt());
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Cant update user");
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Cant delete user");
        }
    }

    @Override
    public List<User> getAll() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> allUsers = new ArrayList<>();
            while (resultSet.next()) {
                User user = getUserFromResult(resultSet);
                user.setRoles(getRolesUser(user.getId()));
                allUsers.add(user);
            }
            return allUsers;
        } catch (SQLException e) {
            throw new DataProcessingException("Cant get All users");
        }
    }

    private void setRolesUser(User user) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String selectQuery = "SELECT role_id FROM roles WHERE role_name = ?";
            String insetQuery = "INSERT INTO users_role (user_id, role_id) VALUES(?,?)";
            PreparedStatement statementSelect = connection.prepareStatement(selectQuery);
            PreparedStatement statementInsert = connection.prepareStatement(insetQuery);

            for (Role role : user.getRoles()) {
                statementSelect.setString(1, role.getRoleName().name());
                ResultSet resultSet = statementSelect.executeQuery();
                if (resultSet.next()) {
                    statementInsert.setLong(1, user.getId());
                    statementInsert.setLong(2, resultSet.getLong(1));
                    statementInsert.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("cant set roles for user");
        }
    }

    private Set<Role> getRolesUser(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT role_name FROM roles "
                    + "INNER JOIN users_role ON roles.role_id = users_role.role_id "
                    + "WHERE users_role.user_id = ? ";
            Set<Role> usersRole = new HashSet<>();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usersRole.add(Role.of(resultSet.getString("role_name")));
            }
            return usersRole;
        } catch (SQLException e) {
            throw new DataProcessingException("Cant get roles of user");
        }
    }

    private User getUserFromResult(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getLong("user_id");
        String login = resultSet.getString("login");
        String name = resultSet.getString("name");
        String password = resultSet.getString("password");
        byte[] salt = resultSet.getBytes("salt");
        User user = new User(name, login, password);
        user.setId(userId);
        user.setSalt(salt);
        return user;
    }
}
