
package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class logincontrol {

    @FXML
    private Label invalidlogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField pass;
    @FXML
    public void validatelogin(ActionEvent e) {
        LoginDatabaseConnection connectnow = new LoginDatabaseConnection();
        Connection connectdb = connectnow.getConnection();
        String usernameadmin = username.getText();
        String password = pass.getText();

        if(usernameadmin.equals(password) ) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/adminhome.fxml"));
                ((Node) (e.getSource())).getScene().getWindow().hide();
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception ep) {
                ep.printStackTrace();
            }
        }
        else {
            String verifylogin = "select count(1) from prof where username='" + username.getText() + "' and pass='" + pass.getText() + "' union select count(1) from user where username='" + username.getText() + "' and pass='" + pass.getText() + "'";
            try {
                Statement statement = connectdb.createStatement();
                ResultSet queryResult = statement.executeQuery(verifylogin);
                while (queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        invalidlogin.setText("Login Successful");
                        try {

                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/newlocation.fxml"));
                            ((Node) (e.getSource())).getScene().getWindow().hide();
                            Parent root1 = fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root1));
                            stage.show();
                        } catch (Exception ep) {
                            ep.printStackTrace();
                        }
                    } else {
                        invalidlogin.setText("Invalid Credentials");
                    }
                }
            } catch (Exception ep) {
                ep.printStackTrace();
            }

        }
    }
    public void enterButtonOnAction (ActionEvent e) {

        if (!username.getText().isBlank() && !pass.getText().isBlank()) {
            validatelogin(e);

        } else {
            invalidlogin.setText("Invalid Username Or Password");
        }
    }



    public void registerbuttonOnAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/register.fxml"));
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }
    public void home(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/DSSLogin.fxml"));
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }
}



