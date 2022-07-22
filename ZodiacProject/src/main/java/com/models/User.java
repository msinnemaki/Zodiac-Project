package com.models;

public class User {
    private Integer user_id;
    private String first_name;
    private String last_name;
    private String username;
    private String password_id;
    private String zodiac;
    private String mood;

    public User() {

    }

    public User(Integer user_id, String first_name, String last_name,
                String username, String password_id, String zodiac, String mood) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password_id = password_id;
        this.zodiac = zodiac;
        this.mood = mood;

    }

    public User(String first_name, String last_name,
                String username, String password_id, String zodiac) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password_id = password_id;
        this.zodiac = zodiac;

    }
    public User(String first_name, String last_name,
                String username, String password_id, String zodiac, String mood) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password_id = password_id;
        this.zodiac = zodiac;
        this.mood = mood;
    }

    public User(Integer user_id, String mood) {
        this.user_id = user_id;
        this.mood = mood;
    }

    public Integer getUser_id()
    {
        return user_id;
    }

    public void setUser_id(Integer user_id)
    {
        this.user_id = user_id;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }


    public String getPassword_id()
    {
        return password_id;
    }

    public void setPassword_id(String password_id)
    {
        this.password_id = password_id;
    }

    public String getZodiac()
    {
        return zodiac;
    }

    public void setZodiac(String zodiac)
    {
        this.zodiac = zodiac;
    }

    public String getMood()
    {
        return mood;
    }

    public void setMood(String mood)
    {
        this.mood = mood;
    }

}