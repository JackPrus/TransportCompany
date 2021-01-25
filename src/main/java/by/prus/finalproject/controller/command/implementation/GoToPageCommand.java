package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToPageCommand implements Command {

    private final String page;

    public GoToPageCommand(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("currentPage", getPage()); // TODO currentPage don't need
        return CommandResult.forward(getPage());
    }

}
