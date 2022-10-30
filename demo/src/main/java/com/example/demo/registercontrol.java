package com.example.demo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDate;

public  class registercontrol extends NullPointerException {
    Stage stage=new Stage();

    @FXML
    public TextField username;
    @FXML
    public TextField lastname;
    @FXML
    public TextField firstname;
    @FXML
    public TextField email;
    @FXML
    private Label label1;
    @FXML
    public   Hyperlink label2;
    @FXML
    public TextField phonenumber;
    @FXML
    public PasswordField pass;

    @FXML
    public ComboBox gender;
    @FXML
    public DatePicker datePicker;

    public void comb3 (MouseEvent mouseEvent) {
        ObservableList data = FXCollections.observableArrayList("Male","Female","Other");
        gender.setItems(data);
    }


    public void savebuttononaction(ActionEvent actionEvent) {
        String Username = username.getText();
        String Firstname = firstname.getText();
        String Lastname = lastname.getText();
        String Email = email.getText();
        String MobileNumber = phonenumber.getText();
        String Password = pass.getText();
        String Gender = gender.getSelectionModel().getSelectedItem().toString();
        LocalDate i = datePicker.getValue();
        Date date1= Date.valueOf(i);
        LoginDatabaseConnection connectnow = new LoginDatabaseConnection();
        Connection connectdb = connectnow.getConnection();
        PreparedStatement psinsert = null;
        PreparedStatement pscheck = null;
        ResultSet resultSet = null;

        try {
            pscheck = connectdb.prepareStatement("select * from user where username= ?");
            pscheck.setString(1, Username);
            resultSet = pscheck.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("User Already Exists...");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("YOU CANNOT USE THIS USERNAME.");
                alert.show();
            } else {

                psinsert = connectdb.prepareStatement("insert into user (firstname , lastname ,phonenumber,email,username ,pass,gender,dob) VALUES (?,?,?,?,?,?,?,?)");
                psinsert.setString(1, Firstname);
                psinsert.setString(2, Lastname);
                psinsert.setString(3, MobileNumber);
                psinsert.setString(4, Email);
                psinsert.setString(5, Username);
                psinsert.setString(6, Password);
                psinsert.setString(7,Gender);
                psinsert.setDate(8,date1);
                psinsert.executeUpdate();
                label1.setText("Details Saved Successfully!");

            }
        } catch (SQLException ep) {
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
    public void user2login(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/login.fxml"));
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
