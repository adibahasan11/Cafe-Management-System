package sample.Authentication;

import Database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Orders.Orders;

import java.io.IOException;

public class LogInController {
    Database database = new Database();

    ObservableList<String> roleList = FXCollections.observableArrayList("Admin", "Employee");
    @FXML
    private ChoiceBox role;

    @FXML
    private void initialize() {
        role.setValue("Admin");
        role.setItems(roleList);
    }

    @FXML
    TextField txt_mail;

    @FXML
    PasswordField txt_pass;

    @FXML
    Label errorLbl;

    public void btn_Login(ActionEvent event) throws IOException {
        String adminMail = txt_mail.getText();
        String adminPass = txt_pass.getText();
        String Role = (String) role.getValue();
        Orders orders = new Orders();
        orders.setRole(Role);

        if (!adminMail.isEmpty() && !adminPass.isEmpty()) {
            if ( Role.equals("Admin")){
                boolean success = database.validateAdminLogin(adminMail, adminPass);
                if (success) {
                    setLblError(Color.GREEN, "Congratulations!");
                    changeSceneForAdmin(event);
                } else {
                    setLblError(Color.TOMATO, "Invalid mail or password");
                }
            }
            else if ( Role.equals("Employee")){
                boolean success = database.validateEmployeeLogin(adminMail, adminPass);
                if (success) {
                    setLblError(Color.GREEN, "Congratulations!");
                    changeSceneForEmp(event);
                } else {
                    setLblError(Color.TOMATO, "Invalid mail or password");
                }
            }
        }
        else
        {
            setLblError(Color.TOMATO, "Not enough information provided");
        }
    }
    private void setLblError(Color color, String text) {
        errorLbl.setTextFill(color);
        errorLbl.setText(text);
    }

    private void changeSceneForAdmin(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("../Admin/AdminHomePage.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    private void changeSceneForEmp(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("../Employee/EmpHomePage.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    public void btn_Register(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
}
