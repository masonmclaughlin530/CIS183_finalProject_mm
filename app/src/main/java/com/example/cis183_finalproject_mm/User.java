package com.example.cis183_finalproject_mm;

public class User
{
    private String username;
    private int userId;
    private String password;


    public User()
    {

    }

    public User(String u, int i,String p)
    {
        username = u;
        userId = i;
        password = p;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
