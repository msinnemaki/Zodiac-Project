package com.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.User;
import com.persistence.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UserServlet extends HttpServlet {

    UserDAO UserDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        //Get current user info

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TO SIGN IN
        processUserLogin(req, resp);
    }

    private void processUserLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();

        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }

        System.out.println(sb);

        String body = sb.toString();

        String[] sepByComma = body.split(",");

        ArrayList<String> values = new ArrayList<>();

        for (String value : sepByComma) {
            value = value.replaceAll("\\{", "").replaceAll("}", "").replaceAll("\"", "");
            System.out.println(value);
            String target = value.substring(value.indexOf(":") + 1);
            System.out.println(target);
            values.add(target);
        }

        String username = values.get(0);
        String password = values.get(1);

        User User = UserDAO.loginUser(username, password);

        if (User != null) {
            resp.setStatus(200);

//            //get an HTTP Session
//            HttpSession session = req.getSession();
//            session.setAttribute("user", user);

            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.addHeader("Access-Control-Allow-Origin", "*");

            ObjectMapper om = new ObjectMapper();

            //convert the Java object to a JSON object with the object mapper

            out.println(om.writeValueAsString(User));

            System.out.println("The user with username: " + username + " has logged in");

        } else {
            resp.setStatus(204);
        }
    }
}