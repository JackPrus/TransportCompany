package by.prus.finalproject.controller.command.implementation;

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

public class OrdersWithoutManagerCommand implements Command {

    private OrderService orderService;

    private static final String ORDERS_WITHOUT_MAGAGER = "ordersWithoutManager";
    private static final String ORDERS_WITHOUT_MAGAGER_PAGE = "WEB-INF/view/ordersWithoutManager.jsp";


    public OrdersWithoutManagerCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {

        List<Order> orderList = orderService.getOrdersWithoutManager();

        HttpSession session = request.getSession();
        session.setAttribute(ORDERS_WITHOUT_MAGAGER, orderList);

        return CommandResult.forward(ORDERS_WITHOUT_MAGAGER_PAGE);
    }

}
