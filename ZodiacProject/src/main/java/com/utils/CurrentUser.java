package com.utils;

public class CurrentUser
{
    public static CurrentUser currentUser;
    public static Integer user_ID;
    public static String first_Name;
    public static String last_name;
    public static String username;
    public static String password;
    public static String zodiac;
    public static String mood;

    public CurrentUser()
    {

    }
    public CurrentUser(Integer user_ID,String first_Name, String last_name,
                       String username, String password, String zodiac, String mood)
    {
        this.user_ID = user_ID;
        this.first_Name = first_Name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.zodiac = zodiac;
        this.mood = mood;
    }
}