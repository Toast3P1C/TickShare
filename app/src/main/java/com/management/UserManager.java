package com.management;

import com.authentication.Constants;
import com.authentication.Validator;
import com.model.IUser;
import com.model.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserManager implements IUserManager {
    private List<IUser> userList;
    private List<IUser> contactList;
    private Map<String, String> errorMap;
    private final static Logger LOG = LogManager.getLogger(UserManager.class);

    public UserManager() {
        this.userList = new ArrayList<>();
        this.contactList = new ArrayList<>();
        this.errorMap = new HashMap<>();
    }

    @Override
    public boolean createUser(String name, String lastName, String region, String emailAddress,
                              String password) {
        if (checkUserData(name, lastName, region, emailAddress, password)) {
            IUser user = new User(formatToUpperCase(name), formatToUpperCase(name),
                    formatToUpperCase(region), emailAddress, password);
            return userList.add(user);
        } else {
            LOG.error("Can not create User");
            return false;
        }
    }

    @Override
    public IUser getUserFromEmail(String email) {
        return userList.stream().filter(user -> user.getEmailAddress().equals(email)).findAny().orElse(null);
    }

    @Override
    public boolean addContact(IUser user) { return contactList.add(user);}

    @Override
    public boolean deleteContact(IUser user) { return contactList.removeIf(u -> u.getToken().equals(user.getToken())); }

    @Override
    public List<IUser> getUserList() {
        return userList;
    }

    private String formatToUpperCase(String toUpperCase) {
        String nameToUpperCase = Character.toUpperCase(toUpperCase.charAt(0)) + toUpperCase.substring(1);
        return nameToUpperCase;
    }


    private boolean checkUserData(String name, String lastName, String region, String emailAddress,
                                  String password) {
        if (name == null || name.isEmpty()) {
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(), "Name can not be empty");
            LOG.error("name is null or empty");
            return false;
        }
        if (lastName == null || lastName.isEmpty()) {
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(), "Lastname can not be empty");
            LOG.error("lastname is null or empty");
            return false;
        }
        if (region == null || region.isEmpty()) {
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(), "Region can not be empty");
            LOG.error("region is null or empty");
            return false;
        }
        if (emailAddress == null || emailAddress.isEmpty() || !Validator.validateEmail(emailAddress)) {
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(), "Email can not be empty or does not match pattern: " + Constants.EMAIL_REGEX);
            LOG.error("email is null or empty or dose not match the pattern");
            return false;
        }
        if (password == null || password.isEmpty() || password.length() < Constants.PASSWORDLENGTH) {
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(), "Password can not be empty or is shorter than 7 characters");
            LOG.error("password is null or empty");
            return false;
        }
        return true;
    }

    @Override
    public Map<String, String> getErrorMap() { return errorMap; }

}
