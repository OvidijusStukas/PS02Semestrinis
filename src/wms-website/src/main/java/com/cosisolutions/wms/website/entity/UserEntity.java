package com.cosisolutions.wms.website.entity;

import java.io.Serializable;

/**
 * Created by CosISolutions on 2016-03-07.
 */
public class UserEntity implements Serializable {
    private int id;
    private int enabled;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}