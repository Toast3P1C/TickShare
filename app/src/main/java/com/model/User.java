package com.model;

import com.authentication.PasswordUtils;
import com.authentication.TokenGenerator;

public class User implements IUser {
    private String name;
    private String lastName;
    private String region;
    private String emailAddress;
    private String password;
    private String token;
    private String salt;

    public User(String name, String lastName, String region, String emailAddress, String password) {
        this.name = name;
        this.lastName = lastName;
        this.region = region;
        this.emailAddress = emailAddress;
        this.salt = PasswordUtils.getSalt(30);
        this.password = hashPassword(password);
        token = generateToken();
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
    public String getToken() {
        return token;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getSalt() {
        return salt;
    }


    private String generateToken(){
        TokenGenerator tokenGenerator = new TokenGenerator(20);
    return tokenGenerator.nextString();
    }

    private String hashPassword(String password){
        String passwordToHash = PasswordUtils.generateSecurePassword(password,salt);
        return passwordToHash;
    }

    @Override
    public String toString(){
        return "Name: "+name+", Lastname: "+lastName
                +", Region: "+region+", Email Address: "+emailAddress;
    }
}
