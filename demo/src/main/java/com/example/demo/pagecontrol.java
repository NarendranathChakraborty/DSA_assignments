package com.example.demo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class pagecontrol {
    @FXML
    private Image image1;

    @FXML
    public Label l1;
    @FXML
    public Label l2;
    @FXML
    public Label yos;
    @FXML
    public Label email;
    @FXML
    private ImageView wimg;
    @FXML
    public GridPane grid, grid1,rate;
    @FXML
    public ComboBox locations, services;
    @FXML
    public Label ly1, le1, m1;
    public static String q,a,b,c;
    public List<worker> workers = new ArrayList<>();
    public MyListener myListener;

    public void setworker(worker workers) {
        l1.setText(workers.getName());
        yos.setText(workers.getYear());
        l2.setText(workers.getLname());
        email.setText(workers.getMail());
        image1 = workers.getImgSrc();
        wimg.setImage(image1);
    }

    public void comb2(MouseEvent mouseEvent) {

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

    public void comb1(MouseEvent mouseEvent) {
        try {
            LoginDatabaseConnection connectnow2 = new LoginDatabaseConnection();
            Connection connectdb3 = connectnow2.getConnection();

            ResultSet rs = connectdb3.createStatement().executeQuery("SELECT sname FROM service");
            ObservableList data = FXCollections.observableArrayList();
            while (rs.next()) {
                data.add(new String(rs.getString(1)));
            }

            services.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void get(ActionEvent actionEvent) {
        Button button1 = new Button("Contact");
        button1.setPrefSize(135, 50);
        grid1.add(button1, 0, 1);
        button1.setDefaultButton(true);
        button1.setFont(Font.font(17));
        button1.setOnAction(actionEvent1 -> {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/Contact.fxml"));
                ((Node) (actionEvent1.getSource())).getScene().getWindow().hide();
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception ep) {
                ep.printStackTrace();
            }

        });
        ly1.setText("Years");
        m1.setText("Mail :");
        le1.setText("Experience :");
        Button button = new Button("Rate");
        button.setPrefSize(135, 50);
        rate.add(button, 0, 1);
        button.setDefaultButton(true);
        button.setFont(Font.font(17));
        button.setOnAction(actionEvent1 -> {
            try {
                LoginDatabaseConnection connectnow10 = new LoginDatabaseConnection();
                Connection connectdb11 = connectnow10.getConnection();
                String t1 = "select pid from prof where firstname ='"+l1.getText()+"'and email ='"+email.getText()+"'";

                try {
                    Statement statement = connectdb11.createStatement();
                    ResultSet queryResult = statement.executeQuery(t1);
                    if (queryResult.next()){
                        int a = queryResult.getInt("pid");
                        String enter ="insert into rating (pid) values('"+a+"')";
                        statement.executeUpdate(enter);
                    }
                } catch (Exception ep) {
                        ep.printStackTrace();
                    }
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/rate.fxml"));
                ((Node) (actionEvent1.getSource())).getScene().getWindow().hide();
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception ep) {
                ep.printStackTrace();
            }
        });

        workers.addAll(getData());
        if (workers.size() > 0) {
            setworker(workers.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(worker Worker)
                {
                    setworker(Worker);
                    q = l1.getText();
                    a=l2.getText();
                    b=email.getText();
                    c=le1.getText();
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < workers.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/demo/fxml/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                itemcontroller itemController = fxmlLoader.getController();
                itemController.setData(workers.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setHgap(15);
                grid.setVgap(5);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10, 10, 10, 10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<worker> getData() throws NullPointerException {
        List<worker> workers = new ArrayList<>();
        worker Worker1;
        LoginDatabaseConnection connectnow5 = new LoginDatabaseConnection();
        Connection connectdb5 = connectnow5.getConnection();
        PreparedStatement psinsert = null;
        PreparedStatement pscheck = null;
        ResultSet resultSet = null;
        String lo = locations.getSelectionModel().getSelectedItem().toString();
        String serve = services.getSelectionModel().getSelectedItem().toString();

        try {
            pscheck = connectdb5.prepareStatement("select * from prof where service ='" + serve + "' and location='" + lo + "'");
            resultSet = pscheck.executeQuery();

            while (resultSet.next()) {
                Worker1 = new worker();
                Worker1.setName(resultSet.getString("firstname"));
                Blob b = resultSet.getBlob("image");
                InputStream inputStream = b.getBinaryStream();
                Image image = new Image(inputStream);
                Worker1.setImgSrc(image);
                Worker1.setLname(resultSet.getString("lastname"));
                Worker1.setYear(resultSet.getString("yos"));
                Worker1.setMail(resultSet.getString("email"));
                workers.add(Worker1);
            }

        } catch (SQLException ep) {
            ep.printStackTrace();
        }

        return workers;
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

