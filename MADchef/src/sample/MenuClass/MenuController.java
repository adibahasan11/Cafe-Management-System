package sample.MenuClass;

import Connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Order.Orders;

import java.io.IOException;
import java.sql.*;

public class MenuController {

    public MenuQueryClass m_Class = new MenuQueryClass();
    Orders orders = new Orders();
    String role = orders.getRole();


    @FXML
    private TextField I_id;
    @FXML
    private TextField I_name;
    @FXML
    private TextField I_price;

    @FXML
    private TableView<Menu> table;
    @FXML
    private TableColumn<Menu, Integer> ItemId;
    @FXML
    private TableColumn<Menu, String> ItemName;
    @FXML
    private TableColumn<Menu, Integer> ItemPrice;

    @FXML
    private void initialize() throws Exception {
        ItemId.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("ItemId"));
        ItemName.setCellValueFactory(new PropertyValueFactory<Menu, String>("ItemName"));
        ItemPrice.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("ItemPrice"));
        ObservableList<Menu> menuList = MenuQueryClass.getAllRecords();
        PopulateTable(menuList);
    }

    public void PopulateTable(ObservableList<Menu> menuList) {
        table.setItems(menuList);
    }


    public void DeleteItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(I_id.getText());
        m_Class.deleteMenu(id);
        ObservableList<Menu> menuList = MenuQueryClass.getAllRecords();
        PopulateTable(menuList);
        I_id.clear();

    }

    public void UpdateItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(I_id.getText());
        String name = I_name.getText();
        int price = Integer.parseInt(I_price.getText());
        m_Class.updateMenu(id, name, price);
        ObservableList<Menu> menuList = MenuQueryClass.getAllRecords();
        PopulateTable(menuList);
        I_id.clear();
        I_name.clear();
        I_price.clear();


    }

    public void addItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        //String id = I_id.getText();
        String name = I_name.getText();
        int price = Integer.parseInt(I_price.getText());

        m_Class.addMenu(name, price);
        ObservableList<Menu> menuList = MenuQueryClass.getAllRecords();
        PopulateTable(menuList);
        //I_id.clear();
        I_name.clear();
        I_price.clear();

    }

    public void backButton(ActionEvent event) throws IOException {

        Parent HomePage = null;

        if (role.equals("Admin")) {

            HomePage = FXMLLoader.load(getClass().getResource("../Admin/AdminHomePage.fxml"));
        } else if (role.equals("Employee")) {
            HomePage = FXMLLoader.load(getClass().getResource("../Employee/EmpHomePage.fxml"));
        }
        Scene HomeScene = new Scene(HomePage, 1200, 680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
}
