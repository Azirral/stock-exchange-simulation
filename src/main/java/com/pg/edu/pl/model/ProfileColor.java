package com.pg.edu.pl.model;

import lombok.Getter;

import java.awt.*;

@Getter
public enum ProfileColor {
    ORANGE(new Color(255, 127, 0)),
    YELLOW(new Color(255, 255, 0)),
    CHARTREUSE_GREEN(new Color(127, 255, 0)),
    GREEN(new Color(0, 255, 0)),
    SPRING_GREEN(new Color(0,255,127)),
    CYAN(new Color(0,255,255)),
    AZURE(new Color(0,127,255)),
    BLUE(new Color(0,0,255)),
    VIOLET(new Color(127,0,255)),
    MAGENTA(new Color(255,0,255)),
    ROSE(new Color(255,0,127)),
    RED(new Color(255, 0, 0));

    private Color profileColor;

    private ProfileColor(Color profileColor) {
        this.profileColor=profileColor;
    }
}

