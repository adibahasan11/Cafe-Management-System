package sample.Orders;

import Database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class PlaceOrder extends Orders{

    @FXML
    TextField txt_o_id;

    @FXML
    TextField txt_item_id;

    @FXML
    TextField txt_quantity;

    Database database = new Database();

    public void backButtonPress(ActionEvent event) throws IOException {
        if (role.equals("Admin")) {
            Parent HomePage = FXMLLoader.load(getClass().getResource("../Admin/AdminHomePage.fxml"));
            Scene HomeScene = new Scene(HomePage, 781, 508);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(HomeScene);
            window.show();
        }
        else if (role.equals("Employee")){
            Parent HomePage = FXMLLoader.load(getClass().getResource("../Employee/EmpHomePage.fxml"));
            Scene HomeScene = new Scene(HomePage, 781, 508);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(HomeScene);
            window.show();
        }
    }

    public void PlaceFinalOrder(ActionEvent event) throws IOException {
        int order = parseInt(txt_o_id.getText());
        int item = parseInt(txt_item_id.getText());
        int quantity = parseInt(txt_quantity.getText());

        database.placeOrder(order, item, quantity);
        changeScene(event);
    }
    public void PlaceMoreOrder() {
        int order = parseInt(txt_o_id.getText());
        int item = parseInt(txt_item_id.getText());
        int quantity = parseInt(txt_quantity.getText());

        database.placeOrder(order, item, quantity);
        txt_item_id.clear();
        txt_quantity.clear();
    }
    public void setOrderId(){
        int id = database.getID();
        if (id != 0) {
            txt_o_id.setText(Integer.toString(id));
            System.out.println(id);
        }
    }
    private void changeScene(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("../Orders/Bill.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
}
