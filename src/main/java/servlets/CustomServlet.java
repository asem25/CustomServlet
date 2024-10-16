package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/help-service/v1/support")
public class CustomServlet extends HttpServlet {


    private List<String> strings = new ArrayList<>();
    public CustomServlet(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintWriter printWriter = resp.getWriter();


        Random random = new Random();

        String responseString = strings.get(random.nextInt(strings.size()));

        printWriter.println(" " + responseString);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        String param = req.getParameter("param");



        out.println("Parameter: " + param);

    }
}
