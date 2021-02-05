package sample.MenuClass;

import Connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuQueryClass {
    PreparedStatement ps;
    ConnectionClass connectionClass = new ConnectionClass();
    Connection con = connectionClass.getConnection() ;
   // MenuController controller;

    public void updateMenu(int id, String name, int price)
    {
        try {
            String sql = "UPDATE MENU SET ITEM_NAME = ? , PRICE = ? WHERE ITEM_ID = ?";
            ps= con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setInt(2,price);
            ps.setInt(3,id);
            ps.execute();
            System.out.println("updated");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteMenu(int id)
    {
        try {
            String sql = "DELETE FROM MENU WHERE ITEM_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            System.out.println("deleted");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void addMenu( String name, int price)
    {
        try {
            String sql = "INSERT INTO MENU (ITEM_NAME,PRICE) VALUES (?,?)";
            ps= con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setInt(2,price);
            ps.executeUpdate();
            System.out.println("Menu added");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Menu> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM MENU";
        ObservableList<Menu> menulist = FXCollections.observableArrayList();

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            PreparedStatement ps = connectionClass.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("Item_Id");
                String name = resultSet.getString("Item_Name");
                int price = resultSet.getInt("Price");

                Menu menu = new Menu(id,name,price);

                menulist.add(menu);
            }
            return menulist;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return menulist;
    }
}
