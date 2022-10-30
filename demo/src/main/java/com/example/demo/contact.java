package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
public class contact extends pagecontrol implements Initializable{
    @FXML
    public Label fname,lname,email1,phonenumber1,service1,location1,gender1,exper,dob;
    @FXML
    public ImageView pimg1;
    @FXML
    public Rating r2;
    public static String w,a1,b1,c1,d1;
    public static String o;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        w=q;
        a1=a;
        b1=b;
        c1=c;
        try{
            LoginDatabaseConnection connectnow10 = new LoginDatabaseConnection();
            Connection connectdb11 = connectnow10.getConnection();
            String d = "select * from prof where firstname ='"+w+"'";
            Statement statement = connectdb11.createStatement();
            ResultSet queryResult = statement.executeQuery(d);
            while(queryResult.next()){
                fname.setText(queryResult.getString("firstname"));
                lname.setText(queryResult.getString("lastname"));
                email1.setText(queryResult.getString("email"));
                phonenumber1.setText(queryResult.getString("phonenumber"));
                service1.setText(queryResult.getString("service"));
                location1.setText(queryResult.getString("location"));
                exper.setText(queryResult.getString("yos")+"     Years");
                gender1.setText(queryResult.getString("gender"));
                dob.setText(queryResult.getString("dob"));
                Blob b1 = queryResult.getBlob("image");
                InputStream inputStream = b1.getBinaryStream();
                Image image2 = new Image(inputStream);
                pimg1.setImage(image2);
            }
        }catch (SQLException ep){
            ep.printStackTrace();
        }
        try{
            LoginDatabaseConnection connectnow10 = new LoginDatabaseConnection();
            Connection connectdb11 = connectnow10.getConnection();
            String d9 = "select pid from prof where firstname='"+w+"'and lastname='"+a+"' ";
            Statement statement = connectdb11.createStatement();
            ResultSet queryResult = statement.executeQuery(d9);
            while(queryResult.next()) {
                o=queryResult.getString("pid");
                d1=o;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            LoginDatabaseConnection connectnow10 = new LoginDatabaseConnection();
            Connection connectdb11 = connectnow10.getConnection();
            String d = "select avg(rating1) as d from rating where pid = '"+d1+"' ";
            Statement statement = connectdb11.createStatement();
            ResultSet queryResult = statement.executeQuery(d);
            while(queryResult.next()) {
                r2.setRating(queryResult.getDouble("d"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void back(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/newlocation.fxml"));
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }
    public void out(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/DSSLogin.fxml"));
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }
}
