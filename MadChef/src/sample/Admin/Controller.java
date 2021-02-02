package sample.Admin;

import Database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {
    public static String mail;
    Database database = new Database();

    public void addStaff(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("EmployeeTable.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
    public void takeOrder(ActionEvent event) throws IOException {
        database.takeOrder();
        Parent HomePage = FXMLLoader.load(getClass().getResource("../Orders/PlaceOrder.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    public void viewMenu(ActionEvent event){
    }
    public void passInfo(String email){
        mail = email;
    }
    public void editInfo(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("AdminProfile.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();

        Database database = new Database();
        database.viewAdminProfile(mail);
        AdminProfileController adminProfileController = new AdminProfileController();
        adminProfileController.showInfo();
    }

    public void LogOut(ActionEvent event)throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("../Authentication/Login.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
}
