package com.persistence;

import com.utils.ConnectionManager;
import com.utils.CurrentUser;
import com.utils.CustomCRUDInterface;
import com.models.User;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO implements CustomCRUDInterface<User> {

    Connection connection;

    public UserDAO()
    {
        connection = ConnectionManager.getConnection();
    }

    @Override
    public Integer create(User user)
    {
        try
        {
            String sql = "INSERT INTO zodiacs (user_id, first_name, last_name, username, password_id, zodiac, mood) VALUES (default,?,?,?,?,?,?) ";
            PreparedStatement pstat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstat.setString(1, user.getFirst_name());
            pstat.setString(2, user.getLast_name());
            pstat.setString(3, user.getUsername());
            pstat.setString(4, user.getPassword_id());
            pstat.setString(5, user.getZodiac());
            pstat.setString(6, user.getMood());


            pstat.executeUpdate();

            ResultSet rs = pstat.getGeneratedKeys();

            //the cursor is initially placed before the first element in the Result Set
            //You need to advance it once to access the first element
            rs.next();

            return rs.getInt(1);

        }
        catch (Exception e)
        {
            System.out.println("Error in the userDAO: "+ e.getMessage());
        }
        return null;
    }

    @Override
    public User read(Integer id)
    {
        try
        {
            String sql = "SELECT * FROM zodiacs WHERE user_id = ?";
            PreparedStatement pstat = connection.prepareStatement(sql);

            pstat.setInt(1,id);

            ResultSet rs = pstat.executeQuery();
            User user = new User();
            while (rs.next())
            {
                user.setUser_id(rs.getInt("user_id"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword_id(rs.getString("password_id"));
                user.setZodiac(rs.getString("zodiac"));
                user.setMood(rs.getString("mood"));
            }
            return user;
        }

        catch (Exception e)
        {
            System.out.println("This is the UserDao" + e.getMessage());
        }
        return null;
    }

    @Override
    public User update(User user)
    {
        try{
            String sql = "UPDATE zodiacs SET mood=? WHERE user_id=?";

            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getMood());
            pstmt.setInt(2,user.getUser_id());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            while (rs.next()){
                user.setMood(rs.getString("mood"));
            }

            return user;

        } catch (Exception e){
            System.out.println("Error in UserDAO update() method "+e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer id)
    {
        return false;
    }
    public User loginUser(String username, String password) {

        try {
            String sql = "SELECT * FROM zodiacs WHERE username = ?";
            PreparedStatement pstat = connection.prepareStatement(sql);
            pstat.setString(1, username);

            ResultSet rs = pstat.executeQuery();

            if (rs.next()) {

                if (rs.getString("password_id").equals(password)) {


                    return new User(rs.getInt("user_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("username"),
                            rs.getString("password_id"),
                            rs.getString("zodiac"),
                            rs.getString("mood"));
                }
            }
        } catch (Exception e) {
            System.out.println("This is the userDAO" + e.getMessage());
        }
        return null;
    }



}