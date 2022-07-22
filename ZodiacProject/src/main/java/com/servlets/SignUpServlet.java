package com.servlets;

        import com.persistence.UserDAO;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.models.User;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.PrintWriter;
        import java.util.*;

        import static com.utils.CurrentUser.*;


public class SignUpServlet extends HttpServlet {

    UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doPost(req, resp);
        registerUser(req, resp);

    }

    private void registerUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        BufferedReader br = req.getReader();
        String line = br.readLine();


        StringBuilder sb = new StringBuilder();




        // Here we are going to read through each line of our req.getReader
        // It is possible for there to be only 1 line in our br
        // but creating a while loop is the safest way

        while (line != null){
            sb.append(line);
            line = br.readLine();
        }


        System.out.println(sb);

        String body = sb.toString();

        String[] sepByComma = body.split(",");


        ArrayList<String> values = new ArrayList<>();

        // will print out index 0 and 1 that is separated by comma
        for(String value: sepByComma) {
//            System.out.println(value);

            // Here we want to trim all of the excess symbols as well as
            // keys and key the values
            // note we can add multiple replaceAll functions
            // removes all "" and , commands

            value = value.replaceAll("\\{","").replaceAll("}","").replaceAll("\"","");

            String target = value.substring(value.indexOf(":") + 1);

            System.out.println(target);
            values.add(target);

        }


        String first_Name = values.get(0);
        String last_name = values.get(1);
        String username = values.get(2);
        String password = values.get(3);
        String zodiac = values.get(4);
//        String mood = values.get(5);

        // Here we are doing the logic to create
        User user = new User(first_Name, last_name, username, password, zodiac);

        userDAO.create(user);

        if(user != null){
            resp.setStatus(200);

            PrintWriter out = resp.getWriter();

            resp.setContentType("application/json");

            resp.addHeader("Access-Control-Allow-Origin","*");


            ObjectMapper om = new ObjectMapper();

            // Convert the object with the mapper

            out.println(om.writeValueAsString(user));

            System.out.println("Account has been created with " + username);

        } else {
            resp.setStatus(204);
        }
    }
}