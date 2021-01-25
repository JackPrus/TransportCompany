package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {

    private static final String MAIN_PAGE = "WEB-INF/view/main.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {
        HttpSession session =request.getSession();
        session.invalidate();
        return CommandResult.forward(MAIN_PAGE);
    }
}
