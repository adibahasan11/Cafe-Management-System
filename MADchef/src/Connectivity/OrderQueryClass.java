package Connectivity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Admin.Employee;
import sample.MenuClass.Menu;
import sample.Order.OrderList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderQueryClass {
    PreparedStatement ps;
    ConnectionClass connectionClass = new ConnectionClass();
    Connection con = connectionClass.getConnection() ;


    public void delete(String id) {
        String delSql = "DELETE FROM BILL WHERE ORDER_ID = ?";
        String delSql2 = "DELETE FROM ORDER_FROM_MENU WHERE ORDER_ID =?";
        String delSql3 = "DELETE FROM ORDERS WHERE ORDER_ID =?";
        try {
            PreparedStatement ps = con.prepareStatement(delSql);
            PreparedStatement ps2 = con.prepareStatement(delSql2);
            PreparedStatement ps3 = con.prepareStatement(delSql3);
            ps.setString(1, id);
            ps2.setString(1,id);
            ps3.setString(1,id);
            ps.execute();
            ps2.execute();
            ps3.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ObservableList<OrderList> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM BILL";
        ObservableList<OrderList> orderList = FXCollections.observableArrayList();

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            PreparedStatement ps = connectionClass.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int bil_id = resultSet.getInt(1);
                int order_id = resultSet.getInt(2);
                int price = resultSet.getInt(3);
                String bill_time = resultSet.getString(4);

                System.out.println("bill" +bil_id + "order" + order_id + "price" +price+ "time" + bill_time);

                OrderList list = new OrderList(order_id,bil_id,price,bill_time);

                orderList.add(list);
            }
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderList;
    }

}
