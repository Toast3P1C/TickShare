package com.management;

import com.model.IUser;

import java.util.List;
import java.util.Map;

public interface IUserManager {
    /**
     * Creates an user from given input
     * @param name
     * @param lastName
     * @param region
     * @param emailAddress
     * @param password
     * @return
     */
    public boolean createUser(String name, String lastName, String region, String emailAddress, String password);

    /**
     * Gets an user from his Emailaddress
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
     * Returns the whole userlist which eventually will always be online one user because its stored local
     * @return
     */
    public List<IUser> getUserList();

    /**
     * Returns an Map of errors which happend during validation < MethodName, Error>
     * @return
     */
    public Map<String, String> getErrorMap();

}
