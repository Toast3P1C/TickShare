package com.model;

import androidx.annotation.NonNull;

public interface IUser {
    public String getName();
    public String getLastName();
    public String getRegion();
    public String getEmailAddress();
    public String getToken();
    public String getPassword();
    public String getSalt();
}
