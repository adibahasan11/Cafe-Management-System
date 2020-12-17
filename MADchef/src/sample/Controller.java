package sample;

import Connectivity.ConnectionClass;
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
import java.sql.*;

public class Controller {

    @FXML
    TextField txt_mail;

    @FXML
    TextField txt_pass;

    @FXML
    Label errorLbl;

    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    String sqlQuery= "select admin_mail, admin_pass from admin";


    public void btn_Login(ActionEvent event) throws SQLException, IOException {

        String adminMail = txt_mail.getText();

        String adminPass = txt_pass.getText();

        if (adminMail.isEmpty()== false && adminPass.isEmpty()==false) {

            validateLogin(adminMail ,adminPass,event);
        }
         else
        {
            setLblError(Color.TOMATO,"Not enough information provided");
        }
        }

        private void validateLogin(String mail,String password,ActionEvent event)
        {
            try {
                Statement statement= connection.createStatement();
                ResultSet rs = statement.executeQuery(sqlQuery);

                    while (rs.next()) {

                        String email = rs.getString("admin_mail");
                        String pass = rs.getString("admin_pass");
                        System.out.print(email);
                        System.out.print(pass);

                        if (email.equals(mail) && pass.equals(password)) {
                            setLblError(Color.GREEN, "Congrates!");
                            changeScene(event);
                        }
                        else {

                            setLblError(Color.TOMATO, "Invalid mail or password");
                        }
                    }

            }catch (SQLException e)
            {
                System.out.print(e);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }


    private void changeScene(ActionEvent event) throws IOException {


        Parent HomePage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene HomeScene = new Scene(HomePage,900,530);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();

    }



    private void setLblError(Color color, String text) {
        errorLbl.setTextFill(color);
        errorLbl.setText(text);
        System.out.println(text);
    }
}

