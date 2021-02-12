package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.ClientType;
import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.entityservice.ClientService;
import by.prus.finalproject.service.entityservice.LoginService;
import org.apache.catalina.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Manager can edit any client in case information about client has been changed.
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class EditClientCommand implements Command {

    private ClientService clientService;

    private final String ID = "clientId";
    private final String NAME = "name";
    private final String DATA = "data";
    private final String TYPE = "type";

    private final String ERROR_MESSAGE_PARAM = "errorMessage";
    private final String ERROR_MESSAGE_WRONG_DATA = "wrong_info";


    private final String DONE_PAGE = "WEB-INF/view/done.jsp";
    private final String EDIT_CLIENT_PAGE = "WEB-INF/view/editClient.jsp";

    public EditClientCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {

        Client client = null;

        try{
            int id = Integer.parseInt(request.getParameter(ID));
            String name = request.getParameter(NAME);
            String data = request.getParameter(DATA);
            int type = Integer.parseInt(request.getParameter(TYPE));

            if (name==null || name.equals("") || type<=0) {
                request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_DATA);
                return CommandResult.forward(EDIT_CLIENT_PAGE);
            }

            client = new Client();
            client.setIdentity(id);
            client.setName(name);
            client.setData(data);
            client.setClientType(ClientType.getByIdentity(type));

        }catch (NumberFormatException e){
            request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_DATA);
            return CommandResult.forward(EDIT_CLIENT_PAGE);
        } catch (ClassCastException e){
            throw new ServiceException(e);
        }

        clientService.updateClient(client);

        return CommandResult.forward(DONE_PAGE);
    }
}
