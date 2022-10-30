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

public class usert  implements Initializable {
    @FXML
    private TableView<ModelTable1> table;
    @FXML
    private TableColumn<ModelTable1, String> id2;
    @FXML
    private TableColumn<ModelTable1, String> fname2;
    @FXML
    private TableColumn<ModelTable1, String> lname2;
    @FXML
    private TableColumn<ModelTable1, String> mail2;
    @FXML
    private TableColumn<ModelTable1, String> number2;
    @FXML
    private TableColumn<ModelTable1, String> gender2;
    @FXML
    private TextField searchtext;
    @FXML
    private TextField deletetext;
    @FXML
    private Label del;

    ObservableList<ModelTable1> oblist = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoginDatabaseConnection connectnow6 = new LoginDatabaseConnection();
        Connection connectdb6 = connectnow6.getConnection();
        PreparedStatement psinsert = null;
        PreparedStatement pscheck = null;
        ResultSet resultSet = null;
        try {
            pscheck = connectdb6.prepareStatement("select * from user ");
            resultSet = pscheck.executeQuery();
            while (resultSet.next()) {
                oblist.add(new ModelTable1(resultSet.getString("uid"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("phonenumber"),
                        resultSet.getString("gender")));


            }
        } catch (SQLException ex) {
            Logger.getLogger(proft.class.getName()).log(Level.SEVERE, null, ex);
        }
        id2.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname2.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lname2.setCellValueFactory(new PropertyValueFactory<>("lname"));
        mail2.setCellValueFactory(new PropertyValueFactory<>("mail"));
        number2.setCellValueFactory(new PropertyValueFactory<>("number"));
        gender2.setCellValueFactory(new PropertyValueFactory<>("gender"));
        table.setItems(oblist);

        FilteredList<ModelTable1> filteredList = new FilteredList<>(oblist, b -> true);
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
                } else if (modelTable.getGender().toLowerCase().indexOf(searchword) >-1) {
                    return  true;
                }  else
                    return false;
            });
        });
        SortedList<ModelTable1> sortedList = new SortedList<>(filteredList);
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
            pscheck = connectdb7.prepareStatement("delete from user where uid ='" + deletetext.getText() + "'");
            pscheck.executeUpdate();
            del.setText("Deleted Successfully");
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        oblist.removeAll(oblist);
        try {
            pscheck = connectdb7.prepareStatement("select * from user ");
            resultSet = pscheck.executeQuery();
            while (resultSet.next()) {
                oblist.add(new ModelTable1(resultSet.getString("uid"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("phonenumber"),
                        resultSet.getString("gender")));


            }
        } catch (SQLException ex) {
            Logger.getLogger(proft.class.getName()).log(Level.SEVERE, null, ex);
        }
        id2.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname2.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lname2.setCellValueFactory(new PropertyValueFactory<>("lname"));
        mail2.setCellValueFactory(new PropertyValueFactory<>("mail"));
        number2.setCellValueFactory(new PropertyValueFactory<>("number"));
        gender2.setCellValueFactory(new PropertyValueFactory<>("gender"));
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

