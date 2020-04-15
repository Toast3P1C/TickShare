package com.management;

import com.authentication.Constants;
import com.authentication.Validator;
import com.model.IUser;
import com.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserManager {
    private List<IUser> userList = new ArrayList<>();
    private List<IUser> contactList = new ArrayList<>();
    private Map<String,String> errorMap = new HashMap<>();

    public boolean createUser(String name, String lastName, String region, String emailAddress,
    String password){
        if(checkUserData(name, lastName, region, emailAddress, password)){
            IUser user = new User(formatToUpperCase(name), formatToUpperCase(name), formatToUpperCase(region), emailAddress, password);
            addUserToList(user);
            return true;
        }else{
            //TODO: add logger here
            return false;
        }
    }

    public boolean addContact(IUser user){
        return contactList.add(user);
    }
    public boolean deleteContact(final IUser user){
        return contactList.removeIf(u -> u.getToken().equals(user.getToken()));
    }

    private boolean addUserToList(IUser user){
        return userList.add(user);
    }

    public List<IUser> getUserList() {
        return userList;
    }

    private String formatToUpperCase(String toUpperCase) {
        String nameToUpperCase = Character.toUpperCase(toUpperCase.charAt(0)) + toUpperCase.substring(1);
        return nameToUpperCase;
    }
    private boolean checkUserData(String name, String lastName, String region, String emailAddress,
                                  String password){
        //TODO: add logger here
        if(name == null || name.isEmpty()){
            errorMap.put(new Object(){}.getClass().getEnclosingMethod().getName(),"Name can not be empty");
            return false;
        }
        if(lastName == null || lastName.isEmpty()){
            errorMap.put(new Object(){}.getClass().getEnclosingMethod().getName(),"Lastname can not be empty");
            return false;
        }
        if(region == null || region.isEmpty()){
            errorMap.put(new Object(){}.getClass().getEnclosingMethod().getName(),"Region can not be empty");
            return false;
        }
        if(emailAddress == null || emailAddress.isEmpty() || !Validator.validate(emailAddress)){
            errorMap.put(new Object(){}.getClass().getEnclosingMethod().getName(),"Email can not be empty or dose not match pattern: "+Constants.EMAIL_REGEX);
            return false;
        }
        if(password == null || password.isEmpty() || password.length() < Constants.PASSWORDLENGTH){
            errorMap.put(new Object(){}.getClass().getEnclosingMethod().getName(),"Password can not be empty or is shorter than 7 characters");
            return false;
        }
        return true;
    }
    public Map<String,String> getErrorMap(){
        return errorMap;
    }

}
