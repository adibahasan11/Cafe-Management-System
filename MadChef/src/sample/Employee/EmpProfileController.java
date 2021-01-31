package sample.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class EmpProfileController {
    public Label label_id;
    public Label label_name;
    public Label label_email;
    public Label label_pw;
    public Label label_address;
    public Label label_m_id;
    public Label label_designation;
    public Label label_j_date;

    public static String E_ID;
    public static String E_Name;
    public static String E_Mail;
    public static String E_PW;
    public static String E_Address;
    public static int E_M_ID;
    public static String E_Designation;
    public static String E_Join_Date;

    public void getInfo(String id, String name, String email, String pw, String address, int manager_id, String designation, String j_date){
        E_ID = id;
        E_Name = name;
        E_Mail = email;
        E_PW = pw;
        E_Address = address;
        E_M_ID = manager_id;
        E_Designation = designation;
        E_Join_Date = j_date;
    }

    public void showInfo(){
        label_email.setText(E_Mail);
        label_id.setText(E_ID);
        label_name.setText(E_Name);
        label_pw.setText(E_PW);
        label_address.setText(E_Address);
        label_m_id.setText(String.valueOf(E_M_ID));
        label_designation.setText(E_Designation);
        label_j_date.setText(E_Join_Date);
    }
    public void backButtonPress(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("EmpHomePage.fxml"));
        Scene HomeScene = new Scene(HomePage,781,508);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
    public void editInfo(){

    }
}
