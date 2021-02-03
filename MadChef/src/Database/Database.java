package Database;

import sample.Admin.AdminProfileController;
import sample.Admin.AdminHomePage;
import sample.Employee.EmpHomePageController;
import sample.Employee.EmpProfileController;
import sample.Orders.Bill;

import java.sql.*;

public class Database {
        String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        String username = "system";
        String password = "1234";
        String url = "jdbc:oracle:thin:@localhost:1521/XE";

    public boolean validateAdminLogin( String email, String pw ){
        try {
            AdminHomePage controller = new AdminHomePage();
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
                controller.passInfo(email);
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
            EmpHomePageController empHomePageController = new EmpHomePageController();
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
                empHomePageController.passInfo(email);
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
        return true;
    }
    public void takeOrder(){
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(url, username, password);
            String insertQuery = "INSERT INTO ORDERS(O_DATE) VALUES(sysdate)";
            Statement preparedStatement = con.createStatement();
            //preparedStatement.setObject(1, new java.sql.Date());
            preparedStatement.executeUpdate(insertQuery);

            System.out.println("Order Added");
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to register driver. Exception code: " + e);
        }
    }
    public void viewEmpProfile(String email) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(url, username, password);
            String selectQuery = "select e.e_id, e.name, e.email, er.password, e.address, e.manager_id, e.designation, e.joining_date from employee e, employee_register er where e.email = ?";
            PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Profile is viewed");
                String E_ID = resultSet.getString(1);
                String E_Name = resultSet.getString(2);
                String E_Mail = resultSet.getString(3);
                String E_PW = resultSet.getString(4);
                String E_Address = resultSet.getString(5);
                int E_M_ID = resultSet.getInt(6);
                String E_Designation = resultSet.getString(7);
                String E_Join_Date = resultSet.getString(8);

                EmpProfileController employeeProfile = new EmpProfileController();
                employeeProfile.getInfo(E_ID, E_Name, E_Mail, E_PW, E_Address, E_M_ID, E_Designation, E_Join_Date);

                System.out.println(E_ID + E_Name + E_Mail + E_PW + E_Address + E_M_ID + E_Designation + E_Join_Date);
                System.out.println("Connection to database successful");

            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to register driver. Exception code: " + e);
        }
    }
    public void viewAdminProfile(String email) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(url, username, password);
            String selectQuery = "select * from Admin where email = ?";
            PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Profile is viewed");
                String E_ID = resultSet.getString(1);
                String E_Name = resultSet.getString(2);
                String E_Mail = resultSet.getString(3);
                String E_PW = resultSet.getString(4);

                AdminProfileController adminProfile = new AdminProfileController();
                adminProfile.getInfo(E_ID, E_Name, E_Mail, E_PW);

                System.out.println(E_ID + E_Name + E_Mail + E_PW);
                System.out.println("Connection to database successful");

            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to register driver. Exception code: " + e);
        }
    }

    public void ViewMenu() {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(url, username, password);
            String selectQuery = "SELECT ITEM_NAME, CATEGORY_NAME, PRICE FROM MENU m, CATEGORY c where m.CATEGORY_ID = c.CATEGORY_ID";
            PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Connection to database successful");
            while (resultSet.next()) {
                System.out.println("Menu is viewed");
                String Item_Name = resultSet.getString(1);
                String Category_Name = resultSet.getString(2);
                int Price = resultSet.getInt(3);

                System.out.println(Item_Name + " " + Category_Name + " " + Price);

            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to register driver. Exception code: " + e);
        }
    }
    public void placeOrder( int order, int item, int quantity ){
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(url, username, password);
            String insertQuery = "INSERT INTO ORDER_FROM_MENU(ORDER_ID, ITEM_ID, QUANTITY) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setInt(1, order);
            preparedStatement.setInt(2, item);
            preparedStatement.setInt(3, quantity);
            preparedStatement.executeUpdate();
            System.out.println("Order Placed");
            Bill bill = new Bill();
            bill.getOrderID(order);
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to register driver. Exception code: " + e);
        }
    }
    public int getID(){
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(url, username, password);
            String selectQuery = "SELECT MAX(ORDER_ID) FROM ORDERS";
            Statement preparedStatement = con.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(selectQuery);
            if (resultSet.next()) {
                System.out.println("Order is viewed");
                int O_ID = resultSet.getInt(1);
                System.out.println(O_ID);
                System.out.println("Connection to database successful");
                return O_ID;
            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to register driver. Exception code: " + e);
        }
        return 0;
    }
    public void getBill (int order_id) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(url, username, password);
            String selectQuery = "select bill_id, amount from bill where order_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
            preparedStatement.setInt(1, order_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Order from menu is viewed");
                int Bill_ID = resultSet.getInt(1);
                int TotalBill = resultSet.getInt(2);

                Bill bill = new Bill();
                bill.getBillInfo(order_id, Bill_ID, TotalBill);

                System.out.println("Order Id: " + order_id + " Id: " + Bill_ID + " Amount: " + TotalBill);
                System.out.println("Connection to database successful");
            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to register driver. Exception code: " + e);
        }
    }
}
