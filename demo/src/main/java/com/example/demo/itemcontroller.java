package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class itemcontroller {
    @FXML
    private Label name;
    @FXML
    private ImageView img;
    @FXML
    public void click(MouseEvent mouseEvent){
        myListener.onClickListener(Worker);
    }
    public worker Worker;
    public MyListener myListener;

    public void setData(worker Worker,MyListener myListener){
        this.Worker= Worker;
        this.myListener=myListener;
        name.setText(Worker.getName());
       Image image1 =Worker.getImgSrc();
        img.setImage(image1);

    }
}
