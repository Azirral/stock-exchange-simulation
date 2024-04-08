package com.mycompany.mavenproject2.entities;
import lombok.Data;

public abstract @Data class Prediction {
    private String name;

    public Prediction(String name) {
        this.name = name;
    }

    public abstract void predict();
}
