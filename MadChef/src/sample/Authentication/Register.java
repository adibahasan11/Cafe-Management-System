package sample.Authentication;

import Database.Database;
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

import static java.lang.Integer.parseInt;

public class Register {
    Database database = new Database();

    @FXML TextField txt_mail;
    @FXML TextField txt_Name;
    @FXML TextField txt_pass;
    @FXML TextField txt_ID;

    @FXML
    Label errorLbl;

    public void btn_Register(ActionEvent event) throws IOException {
        String EmpMail = txt_mail.getText();
        String EmpName = txt_Name.getText();
        String EmpPass = txt_pass.getText();
        String E_id = txt_ID.getText();

        boolean success = database.RegisterStaff(EmpMail, EmpName, EmpPass, E_id);
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
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
    @FXML
    private void backButtonPress(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
}
