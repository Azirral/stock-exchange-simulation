package com.pg.edu.pl.classes;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Setter
@Getter
public class UserProfile extends Account{
    private Color profileBackground;
    private String name;
    private String surname;

    protected UserProfile(Color profileBackground, String name, String surname,
                          String login, String password, String email) {
        super(login, password, email);
        this.profileBackground = profileBackground;
        this.name = name;
        this.surname = surname;
    }
}
