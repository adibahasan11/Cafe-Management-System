package sample.Orders;

import Database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Bill {
    public static int O_ID;
    public static int bill_ID;
    public static int Bill;
    Database database = new Database();

    public Label txt_b_id1;
    public Label txt_o_id1;
    public Label txt_amount1;

    public void getOrderID(int orderID){
        O_ID = orderID;
        System.out.println("Bill taken");
        database.getBill(O_ID);
    }

    public void generateBill(){
        System.out.println(O_ID + " " + bill_ID + " " + Bill);
        txt_o_id1.setText(Integer.toString(O_ID));
        txt_b_id1.setText(Integer.toString(bill_ID));
        txt_amount1.setText(Integer.toString(Bill));
    }

    public void backButtonPress(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("../Admin/AdminHomePage.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
    public void getBillInfo(int order_id, int Bill_ID, int price){
        O_ID = order_id;
        bill_ID = Bill_ID;
        Bill = price;

        System.out.println(O_ID + " 1st time " + bill_ID + " 1st time " + Bill);
    }
}
