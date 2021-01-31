package sample.Employee;

import Database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmpHomePageController {
    public static String mail;

    public void takeOrder(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("../Orders/EmpTakeOrder.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    public void viewMenu(ActionEvent event) throws IOException {
        Database database = new Database();
        database.ViewMenu();

//        Parent HomePage = FXMLLoader.load(getClass().getResource("../Menu/Menu.fxml"));
//        Scene HomeScene = new Scene(HomePage,781,508);
//
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(HomeScene);
//        window.show();
    }

    public void passInfo(String email){
        mail = email;
    }
    public void editInfo(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("EmpProfile.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();

        Database database = new Database();
        database.viewEmpProfile(mail);
    }
    public void LogOut(ActionEvent event)throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("../Authentication/Login.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
}
