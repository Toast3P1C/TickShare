package com.authentication;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void validateEmailUseCase() {
        assertTrue(Validator.validateEmail("paul.tester@test.de"));
    }
    @Test
    public void validateEmailAtMissing() {
        assertFalse(Validator.validateEmail("paul.testertest.de"));
    }
    @Test
    public void validateEmailIsEmpty() {
        assertFalse(Validator.validateEmail(""));
    }
    @Test
    public void validateEmailIsNull() {
        assertThrows(NullPointerException.class,() ->Validator.validateEmail(null));
    }
    @Test
    public void validateEmailMissingDomain() {
        assertFalse(Validator.validateEmail("paulTester@"));
    }
    @Test
    public void validateEmailContainsSpecialChar() {
        assertFalse(Validator.validateEmail("paulÄÖﬁ&≈”ster@test.de"));
    }
    @Test
    public void validateEmailContainsUmlaut() {
        assertFalse(Validator.validateEmail("Täster@test.de"));
    }
}
