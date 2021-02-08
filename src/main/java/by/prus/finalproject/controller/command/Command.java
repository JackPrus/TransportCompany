package by.prus.finalproject.controller.command;

import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    final String PAGE = "page";
    final String AMOUNT_OF_PAGES = "amountPages";
    final String CURRENT_PAGE = "currentPage";
    final int RECORDS_PER_PAGE = 5;
    final String PARAMETER_COMMAND = "command";

    CommandResult execute (HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException;

}
