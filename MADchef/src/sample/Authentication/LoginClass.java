package sample.Authentication;

import Connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Order.Orders;

import java.io.IOException;
import java.sql.*;

public class LoginClass {

    ObservableList<String> roleList = FXCollections.observableArrayList("Admin", "Employee");
    @FXML
    ChoiceBox role;

    @FXML
    TextField txt_mail;

    @FXML
    PasswordField txt_pass;

    @FXML
    Label errorLbl;

    @FXML
    private void initialize() {
        role.setValue("Admin");
        //role.setValue("Employee");
        role.setItems(roleList);
    }

    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();



    public void btn_Login(ActionEvent event) throws SQLException, IOException {

        String adminMail = txt_mail.getText();
        String adminPass = txt_pass.getText();
        String Role = (String) role.getValue();
        Orders orders = new Orders();
        orders.setRole(Role);

        if (!adminMail.isEmpty() && adminPass.isEmpty() == false) {

            if (Role.equals("Admin")) {
                boolean success = connectionClass.validateAdminLogin(adminMail, adminPass);
                if (success) {
                    setLblError(Color.GREEN, "Congratulations!");
                    changeSceneForAdmin(event);
                } else {
                    setLblError(Color.TOMATO, "Invalid mail or password");
                }
            } else if (Role.equals("Employee")) {
                boolean success = connectionClass.validateEmployeeLogin(adminMail, adminPass);
                if (success) {
                    setLblError(Color.GREEN, "Congratulations!");
                    changeSceneForEmp(event);
                } else {
                    setLblError(Color.TOMATO, "Invalid mail or password");
                }
            }
        }
    }
    private void setLblError(Color color, String text) {
        errorLbl.setTextFill(color);
        errorLbl.setText(text);
        System.out.println(text);
    }

    private void changeSceneForAdmin(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("../Admin/AdminHomePage.fxml"));
        Scene HomeScene = new Scene(HomePage,1200,680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    private void changeSceneForEmp(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("../Employee/EmpHomePage.fxml"));
        Scene HomeScene = new Scene(HomePage,1200,680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    public void EmpRegistration(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("../Authentication/Register.fxml"));
        Scene HomeScene = new Scene(HomePage,1200,680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();

    }
}

