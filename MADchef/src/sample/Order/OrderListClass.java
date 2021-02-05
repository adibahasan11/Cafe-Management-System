package sample.Order;

import Connectivity.OrderQueryClass;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Admin.Employee;


import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderListClass {

    @FXML
    TableView<OrderList> order_table;
    @FXML
    TableColumn <OrderList,Integer>order_id;
    @FXML
    TableColumn <OrderList,Integer>bill_id;
    @FXML
    TableColumn <OrderList,Integer>amount;
    @FXML
    TableColumn <OrderList,String>bill_time;

    Orders orders = new Orders();
    String role = orders.getRole();


    OrderQueryClass queryClass = new OrderQueryClass();

    @FXML
    private void initialize() throws Exception {
        order_id.setCellValueFactory(new PropertyValueFactory<OrderList, Integer>("Order_id"));
        bill_id.setCellValueFactory(new PropertyValueFactory<OrderList, Integer>("Bill_id"));
        amount.setCellValueFactory(new PropertyValueFactory<OrderList, Integer>("Amount"));
        bill_time.setCellValueFactory(new PropertyValueFactory<OrderList, String>("Bill_time"));

        ObservableList<OrderList> orderList = OrderQueryClass.getAllRecords() ;
        PopulateTable(orderList);
    }
    public void PopulateTable(ObservableList<OrderList> orderList) {
        order_table.setItems(orderList);
    }


    public void DeleteItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Stage deleteWindow = new Stage();
        deleteWindow.setHeight(500);
        deleteWindow.setWidth(500);
        deleteWindow.initModality(Modality.APPLICATION_MODAL);

        TextField delID = new TextField();
        delID.setPromptText("Order ID");
        delID.setAlignment(Pos.CENTER);
        delID.setMaxHeight(30);
        delID.setMaxWidth(150);
        Button close = new Button("Close");
        close.setOnAction(e -> deleteWindow.close());
        Button confirm = new Button("Confirm");

        VBox box = new VBox(10);
        box.getChildren().addAll(delID, close, confirm);
        box.setAlignment(Pos.CENTER);

        Scene scene = new Scene(box);
        deleteWindow.setScene(scene);
        deleteWindow.show();

        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                queryClass.delete(delID.getText());
                delID.clear();
                ObservableList<OrderList> orderList = null;
                try {
                    orderList = OrderQueryClass.getAllRecords();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                PopulateTable(orderList);
            }
        });
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
