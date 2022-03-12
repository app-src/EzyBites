package io.github.ashishthehulk.ezybites.Models;

public class PostModel {

    private String imageUrl;

    public PostModel()
    {

    }

    public PostModel(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
