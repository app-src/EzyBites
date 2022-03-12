package io.github.ashishthehulk.ezybites.Models;

public class PostModel {

    private int image;

    public PostModel()
    {

    }
    public PostModel(int image) {
        this.image = image;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
