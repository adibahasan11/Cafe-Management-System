package Database;

import java.sql.*;
public class Database {
        String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        String username = "system";
        String password = "1234";
        String url = "jdbc:oracle:thin:@localhost:1521/XE";

    public boolean validateAdminLogin( String email, String pw ){
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(url, username, password);
            String selectQuery = "SELECT EMAIL, PASSWORD FROM ADMIN WHERE EMAIL = ? AND PASSWORD = ?";
            PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, pw);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("table is run");
                System.out.println("Connection to database successful");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to register driver. Exception code: " + e);
        }
        return false;
    }

    public boolean validateEmployeeLogin( String email, String pw ){
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(url, username, password);
            String selectQuery = "SELECT EMAIL, PASSWORD FROM EMPLOYEE_REGISTER WHERE EMAIL = ? AND PASSWORD = ?";
            PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, pw);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("table is run");
                System.out.println("Connection to database successful");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to register driver. Exception code: " + e);
        }
        return false;
    }

    public void addStaff( String name, String email, String address, int man_ID, String designation ){
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(url, username, password);
            String insertQuery = "INSERT INTO EMPLOYEE(NAME, EMAIL, ADDRESS, MANAGER_ID, DESIGNATION) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, address);
            preparedStatement.setInt(4, man_ID);
            preparedStatement.setString(5, designation);
            preparedStatement.executeUpdate();
            System.out.println("Employee Added");
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to register driver. Exception code: " + e);
        }
    }

    public boolean RegisterStaff( String mail, String name, String passwords, String E_ID ){
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(url, username, password);
            String insertQuery = "INSERT INTO EMPLOYEE_REGISTER(E_ID, NAME, EMAIL, PASSWORD) VALUES(?, ?, ?, ?)";

            String selectQuery = "SELECT E_ID FROM EMPLOYEE WHERE EMAIL = ?";
            PreparedStatement preparedStatement2 = con.prepareStatement(selectQuery);
            preparedStatement2.setString(1, mail);
            ResultSet resultSet = preparedStatement2.executeQuery();
            if (resultSet.next()) {
                PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
                preparedStatement.setString(1, E_ID);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, mail);
                preparedStatement.setString(4, passwords);
                preparedStatement.executeUpdate();
                System.out.println("Employee Registered");

            }
            else
                System.out.println("Employee Not in the system");
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to register driver. Exception code: " + e);
        }
        //return false;
        return true;
    }

}
