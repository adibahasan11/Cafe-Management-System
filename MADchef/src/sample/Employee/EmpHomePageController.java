package sample.Employee;

import Connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class EmpHomePageController {
    public static String mail;

    ConnectionClass connectionClass= new ConnectionClass();
    Connection connection=connectionClass.getConnection();

    public void takeOrder(ActionEvent event) throws IOException {
        connectionClass.takeOrder();
        Parent HomePage = FXMLLoader.load(getClass().getResource("../Orders/EmpTakeOrder.fxml"));
        Scene HomeScene = new Scene(HomePage,1200,680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    public void viewMenu(ActionEvent event) throws IOException {

       Parent HomePage = FXMLLoader.load(getClass().getResource("../MenuClass/Menu.fxml"));
       Scene HomeScene = new Scene(HomePage,1200,680);

       Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
       window.setScene(HomeScene);
        window.show();
    }

    public void passInfo(String email){
        mail = email;
    }
    public void editInfo(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("EmpProfile.fxml"));
        Scene HomeScene = new Scene(HomePage,1200,680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();

    }
    public void LogOut(ActionEvent event)throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("../Authentication/Login.fxml"));
        Scene HomeScene = new Scene(HomePage,1200,680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
}
