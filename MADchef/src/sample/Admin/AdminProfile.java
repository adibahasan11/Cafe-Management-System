package sample.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminProfile {

    public Label label_id;
    public Label label_name;
    public Label label_email;
    public Label label_pw;


    public static String E_ID;
    public static String E_Name;
    public static String E_Mail;
    public static String E_PW;

    public void backButtonPress(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
        Scene HomeScene = new Scene(HomePage,1200,680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    public void editInfo(ActionEvent event) {
    }

    public void showInfo(ActionEvent event) {
        label_email.setText(E_Mail);
        label_id.setText(E_ID);
        label_name.setText(E_Name);
        label_pw.setText(E_PW);
    }
}
