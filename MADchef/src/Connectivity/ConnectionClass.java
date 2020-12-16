package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    private Connection con;

    public Connection getConnection() {
        String username = "SYSTEM";
        String password = "sananda";
        String url = "jdbc:oracle:thin:@localhost:1521/XE";

        try {
            //register the driver class.
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //create the connection object.
            con = DriverManager.getConnection(url, username, password);

            System.out.println("Connection to database successful");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

}
