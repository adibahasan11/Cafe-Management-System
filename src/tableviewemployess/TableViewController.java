package tableviewemployess;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class TableViewController {
    @FXML
    private TableView<employess> table_employess;

    @FXML
    private TableColumn<employess, Integer> col_id;

    @FXML
    private TableColumn<employess, String> col_namee;

    @FXML
    private TableColumn<employess, String> col_email;

    @FXML
    private TableColumn<employess, String> col_date;

    @FXML
    private TableColumn<employess, String> col_designation;

    @FXML
    private TableColumn<employess, Integer> col_salary;

    @FXML
    private TableColumn<employess, String> col_address;
    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_date;

    @FXML
    private TextField txt_designation;

    @FXML
    private TextField txt_salary;

    @FXML
    private TextField txt_address;

    /*ObservableList<employess>listM;
    int index =-1;
    Connection conn =null;
    ResultSet rs =null;
    PreparedStatement pst=null;*/

    }


