package com.authentication;

import com.management.IUserManager;
import com.management.UserManager;
import com.model.User;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordUtilsTest {

    private IUserManager userManager;
    @Before
    public void init(){
        userManager = new UserManager();
        userManager.createUser("Paul","Tester","Hamburg","paul.tester@test.com","password123");
    }

    @Test
    public void verifyUserPassword() {
        assertTrue(PasswordUtils.verifyUserPassword("password123",
                PasswordUtils.generateSecurePassword("password123",userManager.getUserList().get(0).getSalt()
                ),userManager.getUserList().get(0).getSalt()));
    }
}