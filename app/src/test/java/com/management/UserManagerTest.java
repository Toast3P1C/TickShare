package com.management;

import com.authentication.Constants;
import com.model.IUser;
import com.model.User;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserManagerTest {
    //TODO: Add more test cases
    UserManager userManager = null;
    private static final String NAME = "Paul";
    private static final String LAST_NAME = "Tester";
    private static final String REGION = "Testhausen";
    private static final String EMAILADDRESS = "Tester@test.test";
    private static final String PASSWORD = "sicher123";
    private IUser user;



    @Before
    public void init() {
        userManager = new UserManager();
        user = new User(NAME,LAST_NAME,REGION,EMAILADDRESS,PASSWORD);
    }

    @Test
    public void createUser() {
        assertTrue(userManager.createUser(NAME,LAST_NAME,REGION,EMAILADDRESS,PASSWORD));
    }
    @Test
    public void createUserValueNull() {
        assertFalse(userManager.createUser(NAME,LAST_NAME,REGION,EMAILADDRESS,null));
    }
    @Test
    public void createUserValueEmpty() {
        assertFalse(userManager.createUser(NAME,LAST_NAME,REGION,EMAILADDRESS,""));
    }
    @Test
    public void createUserSpecialCharacters() {
        assertTrue(userManager.createUser("á,ä,ö,ü",LAST_NAME,REGION,EMAILADDRESS,PASSWORD));
    }
    @Test
    public void createUserNonValidEmailAddress() {
        assertFalse(userManager.createUser(NAME,LAST_NAME,REGION, Constants.EMAIL_REGEX,PASSWORD));
    }
    @Test
    public void createUserPasswordToShort() {
        assertFalse(userManager.createUser(NAME,LAST_NAME,REGION, Constants.EMAIL_REGEX,"123"));
    }

    @Test
    public void addContactUseCase() {
        assertTrue(userManager.addContact(user));
    }

    @Test
    public void deleteContactUseCase() {
        userManager.addContact(user);
        assertTrue(userManager.deleteContact(user));
    }

    @Test
    public void getUserList() {
        assertNotNull(userManager.getUserList());
    }
    @Test
    public void getUserByEmailAddressUseCase(){
        userManager.createUser(NAME,LAST_NAME,REGION,EMAILADDRESS,PASSWORD);
        assertNotNull(userManager.getUserFromEmail(EMAILADDRESS));
    }
}