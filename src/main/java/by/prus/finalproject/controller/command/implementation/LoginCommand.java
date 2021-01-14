package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.bean.Role;
import by.prus.finalproject.bean.User;
import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.entityservice.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final String USER_ID = "userId";
    private static final String USER_LOGIN = "login";
    private static final String USER_PASSWORD = "password";
    private static final String USER_ROLE = "userRole";
    private static final String USER_PAGE = "WEB-INF/view/userPage.jsp";
    private static final String LOGIN_PAGE = "WEB-INF/view/login.jsp";
    private static final String ERROR_MESSAGE_PARAM = "errorMessage";
    private static final String ERROR_MESSAGE_WRONG_LOGIN_PASSWORD = "wrong_login";
    private static final String CURRENT_PAGE = "currentPage";

    private final LoginService loginService;

    public LoginCommand(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, PersistentException {
        String login = request.getParameter(USER_LOGIN);
        String password = request.getParameter(USER_PASSWORD);
        if (login == null || password == null) {
            request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_LOGIN_PASSWORD);
            return CommandResult.forward(LOGIN_PAGE);
        }
        User user = loginService.login(login, password);
        if (user!=null) {
            Integer userId = user.getIdentity();
            Role role = user.getRole();

            HttpSession session = request.getSession();
            session.setAttribute(USER_ROLE, role);
            session.setAttribute(USER_ID, userId);
            request.setAttribute(CURRENT_PAGE, USER_PAGE);
            return CommandResult.forward(USER_PAGE);
        } else {
            request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_LOGIN_PASSWORD);
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
