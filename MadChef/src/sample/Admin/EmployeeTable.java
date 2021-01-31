package sample.Admin;

import Database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class EmployeeTable {
    String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    String username = "system";
    String password = "1234";
    String url1 = "jdbc:oracle:thin:@localhost:1521/XE";

    @FXML
    private static TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, String> m_idCol;
    @FXML
    private TableColumn<Employee, String> employeeIDCol;
    @FXML
    private TableColumn<Employee, String> nameCol;
    @FXML
    private TableColumn<Employee, String> emailCol;
    @FXML
    private TableColumn<Employee, String> addressCol;
    @FXML
    private TableColumn<Employee, String> j_date;
    @FXML
    private TableColumn<Employee, String> Salary;

    private ObservableList<Employee> data;
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

    public void refreshTable(ActionEvent event){
        //initialize(null, null);
    }

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

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        data = FXCollections.observableArrayList();
//        try {
//            Class.forName(JDBC_DRIVER);
//            Connection con = DriverManager.getConnection(url1, username, password);
//            String selectQuery = "select * from employee";
//            PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                System.out.println("Profile is viewed");
//                String E_ID = resultSet.getString(1);
//                String E_Name = resultSet.getString(2);
//                String E_Mail = resultSet.getString(3);
//                String E_Address = resultSet.getString(4);
//                int E_M_ID = resultSet.getInt(5);
//                String E_Join_Date = resultSet.getString(6);
//                String E_Designation = resultSet.getString(8);
//
//                data.add(new Employee(E_ID, E_Name, E_Mail, E_Address, E_M_ID, E_Designation, E_Join_Date));
//
//                System.out.println(E_ID + E_Name + E_Mail + E_Address + E_M_ID + E_Designation + E_Join_Date);
//                System.out.println("Connection to database successful");
//            }
//            employeeIDCol.setCellValueFactory(new PropertyValueFactory<>("Employee ID"));
//            nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
//            emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
//            m_idCol.setCellValueFactory(new PropertyValueFactory<>("Manager ID"));
//            addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
//            j_date.setCellValueFactory(new PropertyValueFactory<>("Joining Date"));
//            Salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
//            tableView.setItems(data);
//
//        } catch (SQLException e) {
//            System.out.println("Error while connecting to database. Exception code: " + e);
//        } catch (ClassNotFoundException e) {
//            System.out.println("Failed to register driver. Exception code: " + e);
//        }
//    }
}
