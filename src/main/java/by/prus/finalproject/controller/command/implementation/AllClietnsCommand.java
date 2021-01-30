package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.entityservice.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.List;

public class AllClietnsCommand implements Command {

    private ClientService clientService;
    private final String ALL_CLIENTS_PAGE = "WEB-INF/view/myClients.jsp";
    private final String ALL_CLIENTS = "allClients";

    public AllClietnsCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {
        List<Client> clientList = clientService.readAllClients();
        HttpSession session = request.getSession();
        session.setAttribute(ALL_CLIENTS, clientList);
        return CommandResult.forward(ALL_CLIENTS_PAGE);
    }
}
