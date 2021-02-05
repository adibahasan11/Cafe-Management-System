package sample.Order;

import Connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class BillClass extends Orders{

    public static int O_ID;
    public static int bill_ID;
    public static int Bill;
    ConnectionClass cs = new ConnectionClass();

    public Label txt_b_id1;
    public Label txt_o_id1;
    public Label txt_amount1;

    public void getOrderID(int orderID){
        O_ID = orderID;
        System.out.println("Bill taken");
        cs.getBill(O_ID);
    }

    public void BackButton(ActionEvent event) throws IOException {
        Parent HomePage;
        if (role.equals("Admin")) { HomePage = FXMLLoader.load(getClass().getResource("../Admin/AdminHomePage.fxml"));
        }
        else { HomePage = FXMLLoader.load(getClass().getResource("../Employee/EmpHomePage.fxml"));
        }
        Scene HomeScene = new Scene(HomePage, 1200, 680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    public void GenerateBill(ActionEvent event) {
        System.out.println(O_ID + " " + bill_ID + " " + Bill);
        txt_o_id1.setText(Integer.toString(O_ID));
        txt_b_id1.setText(Integer.toString(bill_ID));
        txt_amount1.setText(Integer.toString(Bill));
    }

    public void getBillInfo(int order_id, int Bill_ID, int price){
        O_ID = order_id;
        bill_ID = Bill_ID;
        Bill = price;
        System.out.println(O_ID + " 1st time " + bill_ID + " 1st time " + Bill);
    }
}
