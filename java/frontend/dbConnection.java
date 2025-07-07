package frontend;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "dbPotteryInventory";
        String dbUser = "root";
        String dbPass = "1234";
        String dbUrl = "jdbc:mysql://127.0.0.1:3307/" + databaseName;

        try {
            databaseLink = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return databaseLink;
    }
}
