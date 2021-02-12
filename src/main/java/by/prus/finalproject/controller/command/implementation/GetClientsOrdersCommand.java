package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.entityservice.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Command returns list of orders belongs to some exact Client.
 * This opiton accessed for Client only.
 */
public class GetClientsOrdersCommand implements Command {

    private OrderService orderService;
    private static final String ALL_ORDERS_PAGE = "WEB-INF/view/allorders.jsp";
    private static final String CLIENT_ID = "client_id";
    private static final String ALLORDERS_FOR_CLIENT = "allOrdersForClient";

    public GetClientsOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {

        HttpSession session = request.getSession();
        Client client = new Client();
        client.setIdentity((int)session.getAttribute(CLIENT_ID));
        List<Order> orderList = orderService.getOrdersForClient(client);

        session.setAttribute(ALLORDERS_FOR_CLIENT, orderList);

        return CommandResult.forward(ALL_ORDERS_PAGE);

    }
}
