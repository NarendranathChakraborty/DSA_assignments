package com.example.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class rate {
    @FXML
    public Rating r1;
    int a;
    @FXML
    public Label ol1;
    @FXML
    public void submit(ActionEvent actionEvent){
        Double s1 = r1.getRating();
        LoginDatabaseConnection connectnow10 = new LoginDatabaseConnection();
        Connection connectdb11 = connectnow10.getConnection();
        String t2 = "select pid from rating where rating1 is null";

        try {
            Statement statement = connectdb11.createStatement();
            ResultSet queryResult = statement.executeQuery(t2);
            while(queryResult.next()){
                 a = queryResult.getInt("pid");

            }
            String enter2 = "update rating set rating1 = '"+s1+"' where pid ='"+a+"'";
            statement.executeUpdate(enter2);
        }
             catch (Exception ep) {
                ep.printStackTrace();
            }
        ol1.setText("Rated Successfully !!");
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
}


