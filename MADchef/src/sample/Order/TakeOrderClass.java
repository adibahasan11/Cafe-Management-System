package sample.Order;

import Connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class TakeOrderClass extends Orders {

    @FXML
    TextField txt_o_id;

    @FXML
    TextField txt_item_id;

    @FXML
    TextField txt_quantity;

    ConnectionClass cs = new ConnectionClass();

    public void BackButton(ActionEvent event) throws IOException {
        Parent HomePage = null;
        if (role.equals("Admin")) {
            HomePage = FXMLLoader.load(getClass().getResource("../Admin/AdminHomePage.fxml"));
        }
        else if (role.equals("Employee")){
            HomePage = FXMLLoader.load(getClass().getResource("../Employee/EmpHomePage.fxml"));
        }
        Scene HomeScene = new Scene(HomePage, 1200, 680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    public void PlaceFinalOrder(ActionEvent event) throws IOException {
        int order = parseInt(txt_o_id.getText());
        int item = parseInt(txt_item_id.getText());
        int quantity = parseInt(txt_quantity.getText());

        cs.placeOrder(order, item, quantity);
        changeScene(event);
    }

    public void PlaceMoreOrder(ActionEvent actionEvent) {
        int order = parseInt(txt_o_id.getText());
        int item = parseInt(txt_item_id.getText());
        int quantity = parseInt(txt_quantity.getText());

        cs.placeOrder(order, item, quantity);
        txt_item_id.clear();
        txt_quantity.clear();
    }

    public void GetOrderId(ActionEvent actionEvent) {
        int id = cs.getID();
        if (id != 0) {
            txt_o_id.setText(Integer.toString(id));
            System.out.println(id);
        }
    }
    private void changeScene(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("../Order/BillClass.fxml"));
        Scene HomeScene = new Scene(HomePage,1200,680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
}
