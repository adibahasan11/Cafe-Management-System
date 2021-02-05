package sample.Authentication;

import Connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class RegisterClass {
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = ConnectionClass.getConnection();

    @FXML
    TextField txt_mail;
    @FXML
    TextField txt_Name;
    @FXML TextField txt_pass;
    @FXML TextField txt_ID;

    @FXML
    Label errorLbl;

    public void btn_Register(ActionEvent event) throws IOException {
        String E_id = txt_ID.getText();
        String EmpMail = txt_mail.getText();
        String EmpName = txt_Name.getText();
        String EmpPass = txt_pass.getText();

        boolean success = connectionClass.RegisterStaff(EmpMail, EmpName, EmpPass, E_id);
        if (success) {
            changeScene(event);
        }
        else{
            errorLbl.setTextFill(Color.TOMATO);
            errorLbl.setText("Employee not in the System");
        }
    }

    private void changeScene(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene HomeScene = new Scene(HomePage,1200,680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
    @FXML
    private void backButtonPress(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene HomeScene = new Scene(HomePage,1200,680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
}
