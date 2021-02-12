package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.Manager;
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
 * Comand returns list of Orders belonds to current manager
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class OrdersOfManagerCommand implements Command {

    private OrderService orderService;

    private static final String MANAGER_ID = "manager_id";
    private static final String ORDERS_OF_MANAGER = "ordersOfManager";
    private static final String ORDERS_OF_MAGAGER_PAGE = "WEB-INF/view/ordersOfManager.jsp";

    public OrdersOfManagerCommand(OrderService orderService) {this.orderService = orderService;}

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {

        HttpSession session = request.getSession();
        Manager manager = new Manager();
        manager.setIdentity((int)session.getAttribute(MANAGER_ID));

        String command = request.getParameter(PARAMETER_COMMAND);

        int page = 1;
        if (request.getParameter(PAGE)!=null){
            page = Integer.parseInt(request.getParameter(PAGE));
        }

        int firstPosition = (page-1)*RECORDS_PER_PAGE;
        int positionsQuantity = RECORDS_PER_PAGE;
        int pagesAmount = orderService.findAmountPages(RECORDS_PER_PAGE);

        if (page>pagesAmount){
            throw new ServiceException("page "+page+ "does not exists");
        }

        List<Order> orderList = orderService.getOrdersOfManager(manager, firstPosition, positionsQuantity);

        request.setAttribute(AMOUNT_OF_PAGES,pagesAmount);
        request.setAttribute(CURRENT_PAGE,page);
        request.setAttribute(ORDERS_OF_MANAGER,orderList);
        request.setAttribute(PARAMETER_COMMAND,command);

        return CommandResult.forward(ORDERS_OF_MAGAGER_PAGE);

    }
}
