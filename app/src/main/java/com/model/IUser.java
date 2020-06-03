package com.model;



public interface IUser {
    /**
     * Returns the forename of the user as string
     * @return
     */
    public String getName();

    /**
     * Returns the last name of the user  as string
     * @return
     */
    public String getLastName();

    /**
     * returns the Region the user lives in as string
     * @return
     */
    public String getRegion();

    /**
     * returns the email address of the user as string
     * @return
     */
    public String getEmailAddress();

    /**
     * returns an unique string token for identifying a user
     * @return
     */
    public String getToken();

    /**
     * returns the encrypted password string
     * @return
     */
    public String getPassword();

    /**
     * returns the salt which was used to encrypt the password
     * @return
     */
    public String getSalt();
}
