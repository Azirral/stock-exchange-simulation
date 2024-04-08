package com.mycompany.mavenproject2.entities;

import lombok.Data;

public abstract @Data class Prediction {
    private String name;
    private String description;
    private double cost;

    public Prediction(String name, String description, double confidenceLevel, double cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public abstract void predict();
}
