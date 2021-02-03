package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.ClientType;
import by.prus.finalproject.bean.Role;
import by.prus.finalproject.bean.User;
import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.entityservice.ClientService;
import by.prus.finalproject.service.entityservice.LoginService;
import by.prus.finalproject.service.quotation.RfqService;
import org.apache.catalina.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NewClientCommand implements Command {

    //loginservice need to check out if exists login and password
    private ClientService clientService;
    private LoginService loginService;

    private final String NAME = "name";
    private final String DATA = "data";
    private final String TYPE = "type";
    private final String LOGIN = "login";
    private final String PASSWORD = "password";
    private final String REPEAT_PASSWORD = "repeatpassword";
    private final String EMAIL = "email";
    private final String MANAGER_ID = "manager_id";

    private final String ERROR_MESSAGE_PARAM = "errorMessage";
    private final String ERROR_MESSAGE_WRONG_DATA = "wrong_login";
    private final String ERROR_MESSAGE_WRONG_PASSWORD = "wrong_secondpasword";
    private final String ERROR_MESSAGE_USER_EXiSTS = "user_exists";

    private final String DONE_PAGE = "WEB-INF/view/done.jsp";
    private final String NEW_CLIENT_PAGE = "WEB-INF/view/newClient.jsp";

    public NewClientCommand(ClientService clientService, LoginService loginService) {
        this.clientService = clientService;
        this.loginService = loginService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {

        try{
            String name = request.getParameter(NAME);
            String data = request.getParameter(DATA);
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            String repeatpass = request.getParameter(REPEAT_PASSWORD);
            String email = request.getParameter(EMAIL);
            int type = Integer.parseInt(request.getParameter(TYPE));



            if (type<=0 || login==null || login.equals("") || password==null) {
                request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_DATA);
                return CommandResult.forward(NEW_CLIENT_PAGE);
            }else if (!password.equals(repeatpass)){
                request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_PASSWORD);
                return CommandResult.forward(NEW_CLIENT_PAGE);
            }

            //check if exists user
            User user = loginService.login(login, password);
            if (user!=null) {
                request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_USER_EXiSTS);
                return CommandResult.forward(NEW_CLIENT_PAGE);
            }

            Client client = new Client();
            client.setClientType(ClientType.getByIdentity(type));
            client.setName(name);
            client.setData(data);
            int clientId = clientService.createClient(client);


            user = new User();
            user.setClientId(clientId);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(Role.CLIENT);
            user.setEmail(email);

            clientService.createUser(user);

        }catch (ClassCastException e){
            throw new ServiceException(e);
        }catch (NumberFormatException e) {
            request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_DATA);
            return CommandResult.forward(NEW_CLIENT_PAGE);
        }

        return CommandResult.forward(DONE_PAGE);

    }
}
