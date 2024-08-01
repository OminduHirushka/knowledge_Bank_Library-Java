package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private static dbConnection dbConnection;
    private Connection connection = null;

    private dbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/unitedlibrary", "root", "");
    }

    public static dbConnection getInstance() throws ClassNotFoundException, SQLException {
        if (dbConnection == null) {
            dbConnection = new dbConnection();
        }

        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
