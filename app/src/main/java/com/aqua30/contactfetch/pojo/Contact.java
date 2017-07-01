package com.aqua30.contactfetch.pojo;

import java.io.Serializable;

/**
 * Created by Saurabh(aqua) on 23-11-2016.
 */

public class Contact implements Serializable{

    private String name = "";
    private String number = "";
    private String email = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}