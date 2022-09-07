package shop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class ConnectionUtil {
    private static final String USER = "***";
    private static final String PASSWORD = "***";
    private static final String URL =  "***";
    private static final Logger LOGGER = Logger.getLogger(ConnectionUtil.class);

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find MySQL Driver", e);
        }
    }

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", USER);
        dbProperties.put("password", PASSWORD);
        try {
            Connection connection = DriverManager.getConnection(URL, dbProperties);
            LOGGER.info("Connection to DB done successes ");
            return connection;
        } catch (SQLException e) {
            LOGGER.info("Connection to DB failed... ");
            throw new RuntimeException("Can't establish the connection to DB", e);
        }
    }
}
