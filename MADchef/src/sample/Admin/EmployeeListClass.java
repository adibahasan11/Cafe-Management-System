package sample.Admin;

import Connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeListClass {

    @FXML
    private TableView<Employee> emp_table;
    @FXML
    private TableColumn<Employee, String> EmpId;
    @FXML
    private TableColumn<Employee, String> EmpName;
    @FXML
    private TableColumn<Employee, String> EmpMail;
    @FXML
    private TableColumn<Employee, String> EmpAddress;
    @FXML
    private TableColumn<Employee, Integer> admin_id;
    @FXML
    private TableColumn<Employee, String> j_date;
    @FXML
    private TableColumn<Employee, String> EmpSalary;

    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    PreparedStatement ps;


    @FXML
    private void initialize() throws Exception {
        EmpId.setCellValueFactory(new PropertyValueFactory<Employee, String>("E_ID"));
        EmpName.setCellValueFactory(new PropertyValueFactory<Employee, String>("E_Name"));
        EmpMail.setCellValueFactory(new PropertyValueFactory<Employee, String>("E_Mail"));
        EmpAddress.setCellValueFactory(new PropertyValueFactory<Employee, String>("E_Address"));
        admin_id.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("E_M_ID"));
        j_date.setCellValueFactory(new PropertyValueFactory<Employee, String>("E_Join_Date"));
        EmpSalary.setCellValueFactory(new PropertyValueFactory<Employee, String>("E_Salary"));

        ObservableList<Employee> empList = getAllRecords();
        PopulateTable(empList);
    }

    private void PopulateTable(ObservableList<Employee> empList) {
        emp_table.setItems(empList);
    }

    public static ObservableList<Employee> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "SELECT E_ID,NAME,EMAIL,ADDRESS,ADMIN_ID,JOINING_DATE,SALARY FROM EMPLOYEE";
        ObservableList<Employee> empList = FXCollections.observableArrayList();

        try {
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

                Employee emp = new Employee(id, name, mail, address, Admin_id, j_date, salary);

                empList.add(emp);
            }
            return empList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return empList;
    }


    public void AddEmp(ActionEvent event) throws IOException {

        Parent HomePage = FXMLLoader.load(getClass().getResource("AddEmployee.fxml"));
        Scene HomeScene = new Scene(HomePage, 1200, 680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    @FXML
    private void deleteEmp(ActionEvent event) {

        Stage deleteWindow = new Stage();
        deleteWindow.setHeight(500);
        deleteWindow.setWidth(500);
        deleteWindow.initModality(Modality.APPLICATION_MODAL);

        TextField delID = new TextField();
        delID.setPromptText("Employee ID");
        delID.setAlignment(Pos.CENTER);
        delID.setMaxHeight(30);
        delID.setMaxWidth(150);
        Button close = new Button("Close");
        close.setOnAction(e -> deleteWindow.close());
        Button confirm = new Button("Confirm");

        VBox box = new VBox(10);
        box.getChildren().addAll(delID, close, confirm);
        box.setAlignment(Pos.CENTER);

        Scene scene = new Scene(box);
        deleteWindow.setScene(scene);
        deleteWindow.show();

        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                delete(delID.getText());
                delID.clear();
            }
        });


    }

    public void delete(String id) {
        String delSql = "DELETE FROM EMPLOYEE WHERE E_ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(delSql);
            ps.setString(1, id);
            ps.execute();

            ObservableList<Employee> empList = getAllRecords();
            PopulateTable(empList);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateEmp(ActionEvent actionEvent) {

        Stage updateWindow = new Stage();
        updateWindow.setHeight(500);
        updateWindow.setWidth(500);
        updateWindow.initModality(Modality.APPLICATION_MODAL);

        TextField E_ID = new TextField();
        E_ID.setPromptText("Employee ID");
        E_ID.setAlignment(Pos.CENTER);
        E_ID.setMaxHeight(30);
        E_ID.setMaxWidth(150);

        TextField E_Name = new TextField();
        E_Name.setPromptText("Employee Name");
        E_Name.setAlignment(Pos.CENTER);
        E_Name.setMaxHeight(30);
        E_Name.setMaxWidth(150);

        TextField E_Email = new TextField();
        E_Email.setPromptText("Employee Email");
        E_Email.setAlignment(Pos.CENTER);
        E_Email.setMaxHeight(30);
        E_Email.setMaxWidth(150);

        TextField E_Address = new TextField();
        E_Address.setPromptText("Employee Address");
        E_Address.setAlignment(Pos.CENTER);
        E_Address.setMaxHeight(30);
        E_Address.setMaxWidth(150);



        Button close = new Button("Close");
        close.setOnAction(e -> updateWindow.close());
        Button confirm = new Button("Confirm");

        VBox box = new VBox(10);
        box.getChildren().addAll(E_ID,E_Name,E_Email,E_Address, close, confirm);
        box.setAlignment(Pos.CENTER);

        Scene scene = new Scene(box);
        updateWindow.setScene(scene);
        updateWindow.show();

        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                update(E_ID.getText(),E_Name.getText(),E_Email.getText(),E_Address.getText());
                E_ID.clear();
                E_Name.clear();
                E_Email.clear();
                E_Address.clear();
                ObservableList<Employee> empList = null;
                try {
                    empList = getAllRecords();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                PopulateTable(empList);
            }
        });



    }

    private void update(String e_idText, String e_nameText, String e_emailText, String text) {

        try {
            String sql = "UPDATE EMPLOYEE SET NAME= ? , EMAIL = ? , ADDRESS =?  WHERE E_ID = ?";
            ps= connection.prepareStatement(sql);
            ps.setString(1,e_nameText);
            ps.setString(2,e_emailText);
            ps.setString(3,text);
            ps.setString(4,e_idText);
            ps.execute();
            System.out.println("updated");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void backButton(ActionEvent event) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("../Admin/AdminHomePage.fxml"));
        Scene HomeScene = new Scene(HomePage, 1200, 680);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
}

