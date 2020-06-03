package com.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private final static String NAME = "Paul";
    private final static String LASTNAME = "Tester";
    private final static String REGION = "TestRegion";
    private final static String EMAILADDRESS = "tester@test.de";
    private final static String PASSWORD = "sicher123";
    IUser user = null;

    @Before
    public void init() {
        user = new User(NAME, LASTNAME, REGION, EMAILADDRESS, PASSWORD);
    }

    @Test
    public void getNameTrue() {
        assertTrue(user.getName().equals("Paul"));
    }

    @Test
    public void getWrongName() {
        assertFalse(user.getName().equals("pa"));
    }

    //create user via usermanger because it formats the input
    @Test
    public void getNameCasesensitive() {
        assertFalse(user.getName().equals("paul"));
    }

    @Test
    public void getNameEmpty() {
        assertFalse(user.getName().equals(""));
    }

    @Test
    public void getNameNull() {
        assertFalse(user.getName().equals(null));
    }


    @Test
    public void getLastNameTrue() {
        assertTrue(user.getLastName().equals("Tester"));
    }

    @Test
    public void getWrongLastName() {
        assertFalse(user.getLastName().equals("Wrong"));
    }

    //create user via usermanger because it formats the input
    @Test
    public void getLastNameCasesensitive() {
        assertFalse(user.getLastName().equals("tester"));
    }

    @Test
    public void getLastNameEmpty() {
        assertFalse(user.getLastName().equals(""));
    }

    @Test
    public void getLastNameNull() {
        assertFalse(user.getLastName().equals(null));
    }


    @Test
    public void getRegionTrue() {
        assertTrue(user.getRegion().equals("TestRegion"));
    }

    @Test
    public void getWrongRegion() {
        assertFalse(user.getRegion().equals("somewhere else"));
    }

    //create user via usermanger because it formats the input
    @Test
    public void getRegionCasesensitive() {
        assertFalse(user.getRegion().equals("testRegion"));
    }

    @Test
    public void getRegionEmpty() {
        assertFalse(user.getRegion().equals(""));
    }

    @Test
    public void getRegionNull() {
        assertFalse(user.getRegion().equals(null));
    }

    @Test
    public void getEmailAddressTrue() {
        assertTrue(user.getEmailAddress().equals("tester@test.de"));
    }

    @Test
    public void getWrongEmailAddress() {
        assertFalse(user.getEmailAddress().equals("wrong@test.de"));
    }

    @Test
    public void getEmailAddressEmpty() {
        assertFalse(user.getEmailAddress().equals(""));
    }

    @Test
    public void getEmailAddressNull() {
        assertFalse(user.getEmailAddress().equals(null));
    }

}