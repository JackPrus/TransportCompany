package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class forward our user to contacts page
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class ContactsCommand implements Command {

    private String page = "WEB-INF/view/contacts.jsp";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {
        return CommandResult.forward(page);
    }
}
