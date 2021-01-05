package sample;

import Database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class EmployeeTable {
    public TableView table_employees;
    public TableColumn col_id;
    public TableColumn col_name;
    public TableColumn col_email;
    public TableColumn col_designation;
    public TableColumn col_date;
    public TableColumn col_address;
    public TableColumn col_salary;

    Database database = new Database();

    @FXML
    TextField txt_name;

    @FXML
    TextField txt_email;

    @FXML
    TextField txt_M_id;

    @FXML
    TextField txt_designation;

    @FXML
    TextField txt_address;

    public void AddEmployees(ActionEvent event){
        String name = txt_name.getText();
        String email = txt_email.getText();
        int M_id = parseInt(txt_M_id.getText());
        String designation = txt_designation.getText();
        String address = txt_address.getText();

        database.addStaff(name, email, address, M_id, designation);
        txt_name.clear();
        txt_email.clear();
        txt_M_id.clear();
        txt_designation.clear();
        txt_address.clear();
    }
    public void backButtonPress(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    public void UpdateEmployees(ActionEvent event){

    }

    public void DeleteEmployees(ActionEvent event){
    }
}
