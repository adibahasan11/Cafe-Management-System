package sample;

import Database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {
    public void addStaff(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("EmployeeTable.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    public void editStaff(ActionEvent event){
    }

    public void editMenu(ActionEvent event){
    }

    public void viewMenu(ActionEvent event){
    }

    public void takeOrder(ActionEvent event){
    }

    public void editInfo(ActionEvent event){
    }
    public void LogOut(ActionEvent event)throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("Authentication/Login.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
}
