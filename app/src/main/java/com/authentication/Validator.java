package com.authentication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private Validator() {

    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile(Constants.EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

}
