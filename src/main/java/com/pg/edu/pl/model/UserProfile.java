package com.pg.edu.pl.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Objects;


/**
 * UserProfile is a class that resembles the user's personal and modifiable information
 * */
@Setter
@Getter
public class UserProfile extends Account implements Cloneable, Comparable<UserProfile> {
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

    /**
     * Constructor for users that dont want to choose the background color
     * */
    protected UserProfile(String name, String surname,
                          String login, String password, String email) {
        super(email, login, password);
        this.profileBackground = null;
        this.name = name;
        this.surname = surname;
    }

    protected UserProfile (String password, String login) {
        super(null, login, password);
        this.profileBackground = null;
        this.name = null;
        this.surname = null;
    }

    // Override toString method to print the object
    @Override
    public String toString() {
        return "UserProfile{" +
                "profileBackground=" + profileBackground +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", wallet=" + wallet +
                '}';
    }

    // deep cloning method
    @Override
    public UserProfile clone() {
        try {
            UserProfile cloned = (UserProfile) super.clone();
            cloned.profileBackground = new Color(profileBackground.getRGB()); // Deep clone of Color
            cloned.wallet = wallet.clone(); // Deep clone of Wallet
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Since UserProfile implements Cloneable, this should never happen
        }
    }

    @Override
    public int compareTo(UserProfile o) {
         return this.getPassword().compareTo(o.getPassword()) + this.getLogin().compareTo(o.getLogin());
    }
}
