package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class citycontrol {
    @FXML
    public Label lname;
    @FXML
    private ImageView limg;
    public location l1;


    public mycity oncity1;

    public void setData(location l1, mycity oncity){
        this.l1=l1;
        this.oncity1= oncity;;
        lname.setText(l1.getLname());
        Image image2 = l1.getImages();
        limg.setImage(image2);
    }

}
