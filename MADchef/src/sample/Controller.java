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

    public void btn_Login(ActionEvent event) throws SQLException, IOException {

        String adminMail = txt_mail.getText();

        String adminPass = txt_pass.getText();

        if (adminMail.isEmpty()== false && adminPass.isEmpty()==false) {

            validateLogin(event);
        }
         else
        {
            setLblError(Color.TOMATO,"Failed");
        }
        }

        private void validateLogin(ActionEvent event)
        {
            String sqlQuery= "Select count(1) FROM admin WHERE admin_mail = '" + txt_mail.getText() + "' AND admin_pass = '" + txt_pass.getText()+"'";

            try {
                Statement statement= connection.createStatement();
                ResultSet rs = statement.executeQuery(sqlQuery);

                while (rs.next()) {

                    System.out.println(rs.getInt(1));

                    String mail = rs.getString("admin_mail");
                    System.out.println(mail);

                    if (mail.equals(txt_mail.getText())) {
                        setLblError(Color.GREEN,"Congrates!");
                        //changeScene(event);
                    } else {

                        setLblError(Color.TOMATO,"Invalid");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    private void changeScene(ActionEvent event) throws IOException {


        Parent HomePage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene HomeScene = new Scene(HomePage);

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

