package com.management;

import com.model.IUser;

import java.util.List;
import java.util.Map;

public interface IUserManager {
    /**
     * Creates an user from given input
     * @param name fore name of the user
     * @param lastName
     * @param region the region the user lives in and where he wants to use the app
     * @param emailAddress
     * @param password must be at least 7 chars for security reasons
     * @return
     */
    public boolean createUser(String name, String lastName, String region, String emailAddress, String password);

    /**
     * Gets an user from provided email address
     * Returns null if the user cant be found
     * @param email
     * @return
     */
    public IUser getUserFromEmail(String email);

    /**
     * Adds an user to the personal contactlist
     * @param user
     * @return
     */
    public boolean addContact(IUser user);

    /**
     * Deletes User from Contactlist
     * @param user
     * @return
     */
    public boolean deleteContact(IUser user);

    /**
     * Returns the whole userlist which eventually will always have the size one
     * because users are stored local
     * @return
     */
    public List<IUser> getUserList();

    /**
     * Returns a Map of errors which happend during validation < MethodName, Error>
     * @return
     */
    public Map<String, String> getErrorMap();

}
