package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class adminhome implements Initializable {
    @FXML
    public Label t1, t2, t3, t4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LoginDatabaseConnection connectnow10 = new LoginDatabaseConnection();
            Connection connectdb11 = connectnow10.getConnection();
            String t10 = "select count(uid) as d from user";

            try {
                Statement statement = connectdb11.createStatement();
                ResultSet queryResult = statement.executeQuery(t10);
                if (queryResult.next()) {
                    int a = queryResult.getInt("d");
                    t1.setText(String.valueOf(a));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                LoginDatabaseConnection connectnow14 = new LoginDatabaseConnection();
                Connection connectdb14 = connectnow14.getConnection();
                String t19 = "select count(pid) as d from prof";

                try {
                    Statement statement = connectdb11.createStatement();
                    ResultSet queryResult = statement.executeQuery(t19);
                    if (queryResult.next()) {
                        int b = queryResult.getInt("d");
                        t2.setText(String.valueOf(b));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    LoginDatabaseConnection connectnow15 = new LoginDatabaseConnection();
                    Connection connectdb15 = connectnow15.getConnection();
                    String t16 = "select count(lid) as d from location";

                    try {
                        Statement statement = connectdb11.createStatement();
                        ResultSet queryResult = statement.executeQuery(t16);
                        if (queryResult.next()) {
                            int c = queryResult.getInt("d");
                            t3.setText(String.valueOf(c));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        LoginDatabaseConnection connectnow19 = new LoginDatabaseConnection();
                        Connection connectdb19 = connectnow19.getConnection();
                        String t13 = "select count(sid) as d from service";

                        try {
                            Statement statement = connectdb11.createStatement();
                            ResultSet queryResult = statement.executeQuery(t13);
                            if (queryResult.next()) {
                                int d = queryResult.getInt("d");
                                t4.setText(String.valueOf(d));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ud(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/usertable.fxml"));
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }

    public void pd(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/proftable.fxml"));
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }

    public void ld(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/locatetable.fxml"));
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }

    public void sd(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/servicetable.fxml"));
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