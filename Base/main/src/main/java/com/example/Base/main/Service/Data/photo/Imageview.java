package com.example.Base.main.Service.Data.photo;

import java.util.List;

public class Imageview {
    private Image image;
    private List<Text> text;

    public Imageview(Image image, List<Text> text) {
        this.image = image;
        this.text = text;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Text> getText() {
        return text;
    }

    public void setText(List<Text> text) {
        this.text = text;
    }
}
