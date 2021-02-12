package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.ClientType;
import by.prus.finalproject.bean.User;
import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.entityservice.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command delete client from list of company's client. Can invoke Manager only.
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class DeleteClientCommand implements Command {

    private ClientService clientService;

    private final String ID = "clientId";
    private final String DONE_PAGE = "WEB-INF/view/done.jsp";

    public DeleteClientCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {

        Client client = null;
        User useer = null;

        try{
            int id = Integer.parseInt(request.getParameter(ID));
            client = new Client();
            client.setIdentity(id);

        }catch (ClassCastException e){
            throw new ServiceException(e);
        }

        clientService.deleteClient(client); // also delete user (conected with this client)

        return CommandResult.forward(DONE_PAGE);
    }

}
