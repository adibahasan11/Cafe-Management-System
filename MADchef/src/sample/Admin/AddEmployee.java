package sample.Admin;

import Connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.MenuClass.Menu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class AddEmployee {


    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

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
    @FXML
    private TableView <Employee> emp_table;
    @FXML
    private TableColumn <Employee,String>EmpId;
    @FXML
    private TableColumn <Employee,String> EmpName;
    @FXML
    private TableColumn <Employee,String>EmpMail;
    @FXML
    private TableColumn <Employee,String> EmpAddress;
    @FXML
    private TableColumn <Employee,Integer> admin_id;
    @FXML
    private TableColumn<Employee, String> j_date;
    @FXML
    private TableColumn<Employee, String> EmpSalary;



    @FXML
    private void initialize() throws Exception{
        EmpId.setCellValueFactory(new PropertyValueFactory<Employee,String>("E_ID"));
        EmpName.setCellValueFactory(new PropertyValueFactory<Employee,String>("E_Name"));
        EmpMail.setCellValueFactory(new PropertyValueFactory<Employee,String>("E_Mail"));
        EmpAddress.setCellValueFactory(new PropertyValueFactory<Employee,String>("E_Address"));
        admin_id.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("E_M_ID"));
        j_date.setCellValueFactory(new PropertyValueFactory<Employee,String>("E_Join_Date"));
        EmpSalary.setCellValueFactory(new PropertyValueFactory<Employee,String>("E_Salary"));

        ObservableList<Employee>empList = getAllRecords();
        PopulateTable(empList);
    }

    private void PopulateTable(ObservableList<Employee> empList) {
        emp_table.setItems(empList);
    }



    public void AddEmployees(ActionEvent event) throws SQLException, ClassNotFoundException {
        String name = txt_name.getText();
        String email = txt_email.getText();
        int M_id = parseInt(txt_M_id.getText());
        String designation = txt_designation.getText();
        String address = txt_address.getText();

        connectionClass.addStaff(name, email, address, M_id, designation);
        ObservableList<Employee>empList = getAllRecords();
        PopulateTable(empList);

        txt_name.clear();
        txt_email.clear();
        txt_M_id.clear();
        txt_designation.clear();
        txt_address.clear();
    }
    public void backButtonPress(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
        Scene HomeScene = new Scene(HomePage,1200,680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    public static ObservableList <Employee> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "SELECT E_ID,NAME,EMAIL,ADDRESS,ADMIN_ID,JOINING_DATE,SALARY FROM EMPLOYEE";
        ObservableList<Employee> empList = FXCollections.observableArrayList();

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            PreparedStatement ps = connectionClass.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("E_ID");
                String name = resultSet.getString("NAME");
                String mail = resultSet.getString("EMAIL");
                String address = resultSet.getString("ADDRESS");
                Integer Admin_id = resultSet.getInt("ADMIN_ID");
                String j_date = resultSet.getString("JOINING_DATE");
                String salary = resultSet.getString("SALARY");

                Employee emp = new Employee(id,name,mail,address,Admin_id,j_date,salary);

                empList.add(emp);
            }
            return empList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return empList;
    }

}
