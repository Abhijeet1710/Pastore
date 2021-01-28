package com.example.pastore.Model;

public class Model {

    String title;
    String description;
    String username;
    String password;
    String img;

    public Model(String title, String description, String username, String password, String img) {
        this.title = title;
        this.description = description;
        this.username = username;
        this.password = password;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
