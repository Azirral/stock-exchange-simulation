package com.pg.edu.pl.classes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Account class  is an abstract class that holds variables needed to log into
 * an account and are used in UserProfile class
 * */
@Setter(AccessLevel.PROTECTED)
@Getter(AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Account {
    private String email;
    /** Username used to log into the user's account */
    private String login;
    private String password;

}