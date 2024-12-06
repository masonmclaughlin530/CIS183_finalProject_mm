package com.example.cis183_finalproject_mm;

public class SessionData
{
    public static User loggedInUser;

    public static User getLoggedInUser()
    {
        return loggedInUser;
    }

    public static void setLoggedInUser(User u)
    {
        loggedInUser = u;
    }
}
