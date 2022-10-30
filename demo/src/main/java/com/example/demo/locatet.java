package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class locatet implements Initializable {
    @FXML
    private TableView<t3> table;
    @FXML
    private TableColumn<t3, String> id4;
    @FXML
    private TableColumn<t3, String> fname4;
    @FXML
    private TextField searchtext,searchtext1,searchtext11;
    @FXML
    private TextField deletetext;
    @FXML
    private Label del;
    @FXML
    private Button browse;
    ObservableList<t3> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoginDatabaseConnection connectnow6 = new LoginDatabaseConnection();
        Connection connectdb6 = connectnow6.getConnection();
        PreparedStatement psinsert = null;
        PreparedStatement pscheck = null;
        ResultSet resultSet = null;
        try {
            pscheck = connectdb6.prepareStatement("select * from location ");
            resultSet = pscheck.executeQuery();
            while (resultSet.next()) {
                oblist.add(new t3(resultSet.getString("lid"),
                        resultSet.getString("locatename")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(proft.class.getName()).log(Level.SEVERE, null, ex);
        }
        id4.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname4.setCellValueFactory(new PropertyValueFactory<>("fname"));

        FilteredList<t3> filteredList = new FilteredList<>(oblist, b -> true);
        searchtext.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(modelTable -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchword = newValue.toLowerCase();

                if (modelTable.getId().toLowerCase().indexOf(searchword) > -1) {
                    return true;
                } else if (modelTable.getFname().toLowerCase().indexOf(searchword) > -1) {
                    return true;
                } else
                    return false;
            });
        });
        SortedList<t3> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedList);
    }
    public void delbutton(ActionEvent actionEvent) {
        LoginDatabaseConnection connectnow7 = new LoginDatabaseConnection();
        Connection connectdb7 = connectnow7.getConnection();
        PreparedStatement psinsert = null;
        PreparedStatement pscheck = null;
        ResultSet resultSet = null;
        try {
            pscheck = connectdb7.prepareStatement("delete from location where lid ='" + deletetext.getText() + "'");
            pscheck.executeUpdate();
            del.setText("Deleted Successfully");
        } catch (Exception ep) {
            ep.printStackTrace();
        }
        oblist.removeAll(oblist);
        try {
            pscheck = connectdb7.prepareStatement("select * from location ");
            resultSet = pscheck.executeQuery();
            while (resultSet.next()) {
                oblist.add(new t3(resultSet.getString("lid"),
                        resultSet.getString("locatename")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(proft.class.getName()).log(Level.SEVERE, null, ex);
        }
        id4.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname4.setCellValueFactory(new PropertyValueFactory<>("fname"));
        table.setItems(oblist);
    }
    public void setBrowse(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(browse.getScene().getWindow());
        String filename = file.getAbsolutePath();
        searchtext11.setText(filename);

    }
    public  void submit(ActionEvent actionEvent) throws FileNotFoundException {
        String add =searchtext1.getText();
        InputStream in = new FileInputStream(searchtext11.getText());
        LoginDatabaseConnection connectnow4 = new LoginDatabaseConnection();
        Connection connectdb4 = connectnow4.getConnection();
        PreparedStatement psinsert = null;
        PreparedStatement pscheck = null;
        ResultSet resultSet = null;
        try {
            psinsert = connectdb4.prepareStatement(" Insert into location(locatelogo,locatename) values (?,?)");
            psinsert.setBlob(1,in);
            psinsert.setString(2,add);
            psinsert.executeUpdate();
            del.setText("Added Successfully");
        } catch (Exception ep) {
            ep.printStackTrace();
        }
        oblist.removeAll(oblist);
        try {
            pscheck = connectdb4.prepareStatement("select * from location ");
            resultSet = pscheck.executeQuery();
            while (resultSet.next()) {
                oblist.add(new t3(resultSet.getString("lid"),
                        resultSet.getString("locatename")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(proft.class.getName()).log(Level.SEVERE, null, ex);
        }
        id4.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname4.setCellValueFactory(new PropertyValueFactory<>("fname"));
        table.setItems(oblist);
    }
    public void back(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/adminhome.fxml"));
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


