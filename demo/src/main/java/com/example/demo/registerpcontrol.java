package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public  class registerpcontrol extends NullPointerException  {

    @FXML
    private TextField username;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private TextField phonenumber;
    @FXML
    private TextField textArea;

    @FXML
    private TextField yos;
    @FXML
    public PasswordField pass;
    @FXML
    private Label label1;
    @FXML
    private  Hyperlink label2;
    @FXML
    private ImageView iv;
    @FXML
    private  Button browse;
    public ComboBox locations;
    public ComboBox services;
    public ComboBox gender1;
    @FXML
    public DatePicker datePicker;

    public void comb2 (MouseEvent mouseEvent) {

        try {
            LoginDatabaseConnection connectnow1 = new LoginDatabaseConnection();
            Connection connectdb2 = connectnow1.getConnection();

            ResultSet rs = connectdb2.createStatement().executeQuery("SELECT  locatename FROM location");
            ObservableList data = FXCollections.observableArrayList();
            while (rs.next()) {
                data.add(new String(rs.getString(1)));
            }
            locations.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void comb1(MouseEvent mouseEvent){
        try{
            LoginDatabaseConnection connectnow2 = new LoginDatabaseConnection();
            Connection connectdb3 = connectnow2.getConnection();

            ResultSet rs = connectdb3.createStatement().executeQuery("SELECT sname FROM service");
            ObservableList data = FXCollections.observableArrayList();
            while (rs.next()){
                data.add(new String(rs.getString(1)));
            }

            services.setItems(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void comb3 (MouseEvent mouseEvent) {
        ObservableList data = FXCollections.observableArrayList("Male","Female","Other");
        gender1.setItems(data);
    }

    public void setBrowse(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(browse.getScene().getWindow());
        String filename = file.getAbsolutePath();
        textArea.setText(filename);

    }
    public void submitbuttononaction(ActionEvent actionEvent) throws FileNotFoundException {
        String Username = username.getText();
        String name1 = firstname.getText();
        String name2 = lastname.getText();
        String Email = email.getText();
        String MobileNumber = phonenumber.getText();
        String Password = pass.getText();
        String YearsOFServices =yos.getText();
        String service =services.getSelectionModel().getSelectedItem().toString();
        String location =locations.getSelectionModel().getSelectedItem().toString();
        String gender = gender1.getSelectionModel().getSelectedItem().toString();

        LoginDatabaseConnection connectnow4 = new LoginDatabaseConnection();
        Connection connectdb4 = connectnow4.getConnection();
        PreparedStatement psinsert = null;
        PreparedStatement pscheck = null;
        ResultSet resultSet = null;
        InputStream in = new FileInputStream(textArea.getText());
        LocalDate i = datePicker.getValue();
        Date date1= Date.valueOf(i);

        try {
            pscheck = connectdb4.prepareStatement("select * from prof where username= ?");
            pscheck.setString(1, Username);
            resultSet = pscheck.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("User Already Exists...");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("YOU CANNOT USE THIS USERNAME.");
                alert.show();
            } else {

                psinsert = connectdb4.prepareStatement("INSERT INTO prof(firstname,lastname,phonenumber,email,username,pass,yos,location,service,image,gender,dob) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                psinsert.setString(1, name1);
                psinsert.setString(2, name2);
                psinsert.setString(3, MobileNumber);
                psinsert.setString(4, Email);
                psinsert.setString(5, Username);
                psinsert.setString(6, Password);
                psinsert.setString(7,YearsOFServices);
                psinsert.setString(8,location);
                psinsert.setString(9,service);
                psinsert.setBlob(10,in);
                psinsert.setString(11,gender);
                psinsert.setDate(12,date1);
                psinsert.executeUpdate();
                label1.setText("Details Saved Successfully!");

            }

        } catch (SQLException ep) {
            ep.printStackTrace();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/login.fxml"));
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }catch (Exception ep ){
            ep.printStackTrace();
        }

    }

        public void back (ActionEvent actionEvent){
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
        @FXML
        public void exit2Application (ActionEvent event){
            Platform.exit();
        }
    }



