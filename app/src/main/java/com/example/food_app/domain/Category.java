package com.example.food_app.domain;

public class Category {
    private int Id;
    private String ImagePath;
    private String Name;

    public Category() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePth) {
        ImagePath = imagePth;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
