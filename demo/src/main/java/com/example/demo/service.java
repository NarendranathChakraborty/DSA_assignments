package com.example.demo;

import javafx.scene.image.Image;

public class service {
        private String sname;

        public String getsname() {
            return sname;
        }

        public void setsname(String sname) {
            this.sname = sname;
        }

        public Image getImages() {
            return images;
        }

        public void setImages(Image images) {
            this.images = images;
        }

        private Image images;

    }


