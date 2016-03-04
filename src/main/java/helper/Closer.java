package helper;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Closer {
    public static void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
