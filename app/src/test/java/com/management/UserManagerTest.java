package com.management;

import com.authentication.Constants;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserManagerTest {
    UserManager userManager = null;
    private static final String NAME = "Paul";
    private static final String LAST_NAME = "Tester";
    private static final String REGION = "Testhausen";
    private static final String EMAILADDRESS = "Tester@test.test";
    private static final String PASSWORD = "sicher123";



    @Before
    public void init() {
        userManager = new UserManager();
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
    public void addContact() {
    }

    @Test
    public void deleteContact() {
    }

    @Test
    public void getUserList() {
    }
    @Test
    public void getUserByEmailAddressUseCase(){
        userManager.createUser(NAME,LAST_NAME,REGION,EMAILADDRESS,PASSWORD);
        assertNotNull(userManager.getUserFromEmail(EMAILADDRESS));
    }
}