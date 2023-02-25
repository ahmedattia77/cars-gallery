package com.example.carsgallery;

public class Car {
    private int id;
    private String model;
    private String description;
    private String color;
    private String image;
    private double DPL;

    public Car(int id, String model, String color, String description, String image, double DPL) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.description = description;
        this.image = image;
        this.DPL = DPL;
    }
    public Car(String model, String color, String description, String image, double DPL) {
        this.model = model;
        this.color = color;
        this.description = description;
        this.image = image;
        this.DPL = DPL;
    }
    public Car (){;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}
    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }
    public double getDPL() {
        return DPL;
    }

    public void setDPL(double DPL) {
        this.DPL = DPL;
    }
}
