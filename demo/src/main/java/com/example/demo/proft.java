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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class proft implements Initializable {
    @FXML
    private TableView<ModelTable> table;
    @FXML
    private TableColumn<ModelTable, String> id1;
    @FXML
    private TableColumn<ModelTable, String> fname1;
    @FXML
    private TableColumn<ModelTable, String> lname1;
    @FXML
    private TableColumn<ModelTable, String> mail1;
    @FXML
    private TableColumn<ModelTable, String> number1;
    @FXML
    private TableColumn<ModelTable, String> locate1;
    @FXML
    private TableColumn<ModelTable, String> serve1;
    @FXML
    private TextField searchtext;
    @FXML
    private TextField deletetext;
    @FXML
    private Label del;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoginDatabaseConnection connectnow6 = new LoginDatabaseConnection();
        Connection connectdb6 = connectnow6.getConnection();
        PreparedStatement psinsert = null;
        PreparedStatement pscheck = null;
        ResultSet resultSet = null;
        try {
            pscheck = connectdb6.prepareStatement("select * from prof ");
            resultSet = pscheck.executeQuery();
            while (resultSet.next()) {
                oblist.add(new ModelTable(resultSet.getString("pid"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("phonenumber"),
                        resultSet.getString("location"),
                        resultSet.getString("service")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(proft.class.getName()).log(Level.SEVERE, null, ex);
        }
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname1.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lname1.setCellValueFactory(new PropertyValueFactory<>("lname"));
        mail1.setCellValueFactory(new PropertyValueFactory<>("mail"));
        number1.setCellValueFactory(new PropertyValueFactory<>("number"));
        locate1.setCellValueFactory(new PropertyValueFactory<>("location"));
        serve1.setCellValueFactory(new PropertyValueFactory<>("service"));
        table.setItems(oblist);

        FilteredList<ModelTable> filteredList = new FilteredList<>(oblist, b -> true);
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
                } else if (modelTable.getLname().toLowerCase().indexOf(searchword) > -1) {
                    return true;
                } else if (modelTable.getMail().toLowerCase().indexOf(searchword) > -1) {
                    return true;
                } else if (modelTable.getNumber().toLowerCase().indexOf(searchword) > -1) {
                    return true;
                } else if (modelTable.getLocation().toLowerCase().indexOf(searchword) >-1) {
                   return  true;
                } else if (modelTable.getService().toLowerCase().indexOf(searchword) > -1) {
                    return true;
                } else
                    return false;
            });
        });
        SortedList<ModelTable> sortedList = new SortedList<>(filteredList);
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
            pscheck = connectdb7.prepareStatement("delete from prof where pid ='" + deletetext.getText() + "'");
            pscheck.executeUpdate();
            del.setText("Deleted Successfully");
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        oblist.removeAll(oblist);
        try {
            pscheck = connectdb7.prepareStatement("select * from prof ");
            resultSet = pscheck.executeQuery();
            while (resultSet.next()) {
                oblist.add(new ModelTable(resultSet.getString("pid"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("phonenumber"),
                        resultSet.getString("location"),
                        resultSet.getString("service")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(proft.class.getName()).log(Level.SEVERE, null, ex);
        }
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname1.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lname1.setCellValueFactory(new PropertyValueFactory<>("lname"));
        mail1.setCellValueFactory(new PropertyValueFactory<>("mail"));
        number1.setCellValueFactory(new PropertyValueFactory<>("number"));
        locate1.setCellValueFactory(new PropertyValueFactory<>("location"));
        serve1.setCellValueFactory(new PropertyValueFactory<>("service"));
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
