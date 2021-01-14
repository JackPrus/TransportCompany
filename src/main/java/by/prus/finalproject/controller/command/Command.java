package by.prus.finalproject.controller.command;

import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    CommandResult execute (HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException;

}
