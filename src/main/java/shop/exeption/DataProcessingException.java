package shop.exeption;

import java.sql.SQLException;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message) {
        super(message);
    }

    public DataProcessingException(String message, SQLException exception) {
        super(message,exception);
    }
}
