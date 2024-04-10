package com.pg.edu.pl.classes;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;


/**
 * UserProfile is a class that resembles the user's personal and modifiable information
 * */
@Setter
@Getter
public class UserProfile extends Account{
    /** Color of the user's profile picture */
    private Color profileBackground;
    private String name;
    private String surname;
    /** User's wallet in which user's stocks and credit are kept*/
    private Wallet wallet;

    protected UserProfile(Color profileBackground, String name, String surname,
                          String login, String password, String email) {
        super(login, password, email);
        this.profileBackground = profileBackground;
        this.name = name;
        this.surname = surname;
    }
}
