package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.AcceptPendingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class newlocationcontrol implements Initializable {
    @FXML
    public GridPane grid;
    @FXML
    public GridPane grid1;

    public List<location> locations =new ArrayList<>();
    public List<service> services = new ArrayList<>();
    public mycity mycity1;
    public myservice myservice1;

    public List<location> getData() throws NullPointerException {
        List<location> locations = new ArrayList<>();
        location location1;
        LoginDatabaseConnection connectnow8 = new LoginDatabaseConnection();
        Connection connectdb8 = connectnow8.getConnection();
        PreparedStatement psinsert = null;
        PreparedStatement pscheck = null;
        ResultSet resultSet = null;
        try {
            pscheck = connectdb8.prepareStatement("select * from location ");
            resultSet = pscheck.executeQuery();

            while (resultSet.next()) {
                location1 = new location();
                location1.setLname(resultSet.getString("locatename"));
                Blob b = resultSet.getBlob("locatelogo");
                InputStream inputStream = b.getBinaryStream();
                Image image = new Image(inputStream);
                location1.setImages(image);
                locations.add(location1);
            }

        } catch (SQLException ep) {
            ep.printStackTrace();
        }

        return locations;
    }
    public List<service> getData1() throws NullPointerException {
        List<service> services = new ArrayList<>();
        service service1;
        LoginDatabaseConnection connectnow8 = new LoginDatabaseConnection();
        Connection connectdb8 = connectnow8.getConnection();
        PreparedStatement psinsert = null;
        PreparedStatement pscheck = null;
        ResultSet resultSet = null;
        try {
            pscheck = connectdb8.prepareStatement("select * from service ");
            resultSet = pscheck.executeQuery();

            while (resultSet.next()) {
                service1 = new service();
                service1.setsname((resultSet.getString("sname")));
                Blob b = resultSet.getBlob("slogo");
                InputStream inputStream = b.getBinaryStream();
                Image image = new Image(inputStream);
                service1.setImages(image);
                services.add(service1);
            }

        } catch (SQLException ep) {
            ep.printStackTrace();
        }

        return services;
    }

    public void initialize(URL location, ResourceBundle resources) {
        locations.addAll(getData());
        services.addAll(getData1());
        int column = 0;
        int row = 1;
        try{
            for (int i = 0; i < services.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/demo/fxml/service.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                newservicecontrol newservicecontrol1=fxmlLoader.getController();
                newservicecontrol1.setData1(services.get(i), myservice1);

                if (column == services.size()) {
                    column = 0;

                }

                grid1.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid1.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid1.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid1.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid1.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid1.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid1.setMaxHeight(Region.USE_PREF_SIZE);
                grid1.setHgap(20);
                GridPane.setMargin(anchorPane, new Insets(-20,10,0,0));
            }
            for (int i = 0; i < locations.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/demo/fxml/city.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                citycontrol citycontrol1 =fxmlLoader.getController();
                citycontrol1.setData(locations.get(i), mycity1);

                if (column == locations.size()) {
                    column = 0;

                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                grid.setHgap(20);
                GridPane.setMargin(anchorPane, new Insets(-20,10,0,0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void getprof(ActionEvent actionEvent ){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/market.fxml"));
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
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

