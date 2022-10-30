package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class newservicecontrol {
    @FXML
    public Label sname;
    @FXML
    private ImageView simg;
    public service s1;


    public myservice myservice1;

    public void setData1(service s1, myservice myservice1){
        this.s1=s1;
        this.myservice1= myservice1;;
        sname.setText(s1.getsname());
        Image image2 = s1.getImages();
        simg.setImage(image2);
    }

}
