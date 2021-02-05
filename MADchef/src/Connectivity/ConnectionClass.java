package Connectivity;

import sample.Employee.EmpHomePageController;
import sample.Order.BillClass;

import java.sql.*;

import static sample.Order.BillClass.Bill;

public class ConnectionClass {

    private static Connection con;

    public static Connection getConnection() {
        String username = "MADCHEF";
        String password = "sananda";
        String url = "jdbc:oracle:thin:@localhost:1521/XE";

        try {
            //register the driver class.
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //create the connection object.
            con = DriverManager.getConnection(url, username, password);

            System.out.println("Connection to database successful"); }
            catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); }
        return con; }

    public boolean validateAdminLogin( String email, String pw ){
        try {

            String selectQuery = "SELECT ADMIN_MAIL, ADMIN_PASS FROM ADMIN WHERE ADMIN_MAIL = ? AND ADMIN_PASS = ?";
            PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, pw);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("table is run");
                System.out.println("Connection to database successful");
                //controller.passInfo(email);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        } /*catch (ClassNotFoundException e) {
            System.out.println("Failed to register driver. Exception code: " + e);
        }*/
        return false;
    }

    public void takeOrder(){
        try {

            String insertQuery = "INSERT INTO ORDERS(O_DATE) VALUES(sysdate)";
            Statement preparedStatement = con.createStatement();
            //preparedStatement.setObject(1, new java.sql.Date());
            preparedStatement.executeUpdate(insertQuery);

            System.out.println("Order Added");
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        }
    }


    public boolean validateEmployeeLogin(String email, String pw) {
        try {
            EmpHomePageController empHomePageController = new EmpHomePageController();
            //Class.forName(JDBC_DRIVER);

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
        }
        return false;
    }
        public boolean RegisterStaff( String mail, String name, String passwords, String E_ID ){
            try {

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
            } /*catch (ClassNotFoundException e) {
                System.out.println("Failed to register driver. Exception code: " + e);
            }*/
            return true;
        }

    public void addStaff( String name, String email, String address, int man_ID, String designation ){
        try {

            String insertQuery = "INSERT INTO EMPLOYEE(NAME,EMAIL, ADDRESS, ADMIN_ID, DESIGNATION) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3,address);
            preparedStatement.setInt(4, man_ID);
            preparedStatement.setString(5, designation);
            preparedStatement.executeUpdate();
            System.out.println("Employee Added");
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        } /*catch (ClassNotFoundException e) {
            System.out.println("Failed to register driver. Exception code: " + e);
        }*/
    }

    public void placeOrder(int order, int item, int quantity) {
        try {

            String insertQuery = "INSERT INTO ORDER_FROM_MENU(ORDER_ID, ITEM_ID, QUANTITY) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setInt(1, order);
            preparedStatement.setInt(2, item);
            preparedStatement.setInt(3, quantity);
            preparedStatement.executeUpdate();
            System.out.println("Order Placed");
            System.out.println(order);
            //getBill(order);
            BillClass bill = new BillClass();
            bill.getOrderID(order);
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        }
    }

    public int getID() {
        try {
            String selectQuery = "SELECT MAX(ORDER_ID) FROM ORDERS ";
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
        }
        return 0;
    }

    public void getBill (int order_id) {
        try {
            String selectQuery = "select bill_id,amount from bill where order_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
            preparedStatement.setInt(1, order_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Order from menu is viewed");
                int Bill_ID = resultSet.getInt(1);
                //int o_id = resultSet.getInt(2);
                int TotalBill = resultSet.getInt(2);

                BillClass bill = new BillClass();
                bill.getBillInfo(order_id, Bill_ID, TotalBill);

                System.out.println("Order Id: " + order_id + " Id: " + Bill_ID + " Amount: " + TotalBill);
                System.out.println("Connection to database successful");
            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to database. Exception code: " + e);
        }
    }
}
