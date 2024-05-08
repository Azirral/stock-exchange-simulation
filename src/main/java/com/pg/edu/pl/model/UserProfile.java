package com.pg.edu.pl.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;


/**
 * UserProfile is a class that resembles the user's personal and modifiable information
 * */
@Setter
@Getter
public class UserProfile extends Account implements Cloneable, Comparable<UserProfile>, Comparator<UserProfile> {
    /** Color of the user's profile picture */
    private Color profileBackground;
    private String name;
    private String surname;
    /** User's wallet in which user's stocks and credit are kept*/
    private Wallet wallet;

    protected UserProfile(Wallet wallet, Color profileBackground, String name, String surname,
                          String login, String password, String email) {
        super(UUID.randomUUID(), login, password, email);
        this.profileBackground = profileBackground;
        this.name = name;
        this.surname = surname;
        this.wallet = wallet;
    }

    /**
     * Constructor for users that dont want to choose the background color
     * */
    protected UserProfile(Wallet wallet, String name, String surname,
                          String login, String password, String email) {
        super(UUID.randomUUID(), email, login, password);
        this.profileBackground = null;
        this.name = name;
        this.surname = surname;
        this.wallet = wallet;
    }

    /**
     * Constructor for creating an object with uuid provided
     * */
    protected UserProfile(Wallet wallet, Color profileBackground, String name, String surname,
                          String login, String password, String email, UUID uuid) {
        super(uuid, email, login, password);
        this.profileBackground = profileBackground;
        this.name = name;
        this.surname = surname;
        this.wallet = wallet;
    }

    /**
     * Override toString method to print the object
     */
    @Override
    public String toString() {
        return "UserProfile{" +
                "profileBackground=" + profileBackground +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", wallet=" + wallet +
                '}';
    }

    public String toCSV() {
        return profileBackground.getRed() + "," +
                profileBackground.getGreen() + "," +
                profileBackground.getBlue() + "," +
                name + ',' +
                surname + ',' +
                super.getLogin() + ',' +
                super.getPassword() + ',' +
                super.getEmail() + ',' +
                super.getUuid() + ',' +
                wallet.getUuid();
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

    /**
     * Compares this UserProfile with another UserProfile for order.
     * @param o The UserProfile to be compared.
     * @return A negative integer, zero, or a positive integer as this UserProfile is less than,
     * equal to, or greater than the specified UserProfile.
     */
    @Override
    public int compareTo(UserProfile o) {
         return this.toString().compareTo(o.toString());
    }

    /**
     * Compares two UserProfiles based on their wallet credit. Made for sorting purpose.
     * @param o1 The first UserProfile to be compared.
     * @param o2 The second UserProfile to be compared.
     * @return A negative integer, zero, or a positive integer as the first UserProfile's
     * wallet credit is less than, equal to, or greater than the second UserProfile's wallet credit.
     */
    @Override
    public int compare(UserProfile o1, UserProfile o2) {
        return o1.getWallet().getCredit().compareTo(o2.getWallet().getCredit());
    }
}
