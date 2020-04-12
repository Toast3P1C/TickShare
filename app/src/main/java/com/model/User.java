package com.model;

public class User implements IUser {
    private int id;
    private String name;
    private String lastName;
    private String region;
    private String emailAddress;
    private String password;

    public User(int id, String name, String lastName, String region, String emailAddress, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.region = region;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getRegion() {
        return region;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
