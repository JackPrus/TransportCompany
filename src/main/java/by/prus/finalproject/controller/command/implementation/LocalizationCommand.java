package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LocalizationCommand implements Command {
    private static final String REFERER_HEADER = "Referer";
    private static final String LOCALIZATION = "localization";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String localization = request.getParameter(LOCALIZATION);
        HttpSession session = request.getSession();
        session.setAttribute(LOCALIZATION, localization);
        String page = (String) request.getAttribute("currentPage");
        if (page == null) {
            page = request.getHeader(REFERER_HEADER);
            return CommandResult.redirect(page);
        }
        return CommandResult.redirect(page);
    }
}
