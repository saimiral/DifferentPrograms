package prototype;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    static final String MYSQL_SUB = "jdbc:mysql:";
    static final String DB_SERVER = "//localhost:3306/";
    static final String DB_NAME = "CRS";
    static final String DB_USER = "root";
    static final String DB_PASS = "mysql";
    static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = MYSQL_SUB + DB_SERVER + DB_NAME;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(MYSQL_DRIVER);
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL Driver not found.");
        }
    }
}
