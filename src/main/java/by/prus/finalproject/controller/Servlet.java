package by.prus.finalproject.controller;

import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    public void process (HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String name = request.getParameter("name");
//        response.getWriter().write("<html><body><H2>Hello, "+ name + "!</H2></body></html>");


        try {
            String commandParam = req.getParameter("command");
            Command command = CommandFactory.create(commandParam);
            String page = command.execute(req,resp);
            dispatch(page, req, resp);
        } catch (ServletException e) {
            //Логгирование
            e.printStackTrace();
        }

    }

    private void dispatch(String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(page).forward(req,resp);
    }

}
