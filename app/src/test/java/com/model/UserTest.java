package com.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    IUser user = null;
    @Before
    public void init(){
        user = new User("Paul","Tester","Berlin","tester@test.de","sicher123");
    }

    @Test
    public void getNameTrue() {
        assertTrue(user.getName().equals("Paul"));
    }
    @Test
    public void getWrongName() {
        assertFalse(user.getName().equals("pa"));
    }
    @Test
    public void getNameCasesensitive() {
        assertTrue(user.getName().equals("paul"));
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
    public void getLastName() {
    }

    @Test
    public void getRegion() {
    }

    @Test
    public void getEmailAddress() {
    }

    @Test
    public void getPassword() {
    }
}