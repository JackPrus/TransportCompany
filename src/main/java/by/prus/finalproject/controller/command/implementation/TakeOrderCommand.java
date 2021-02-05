package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.Manager;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.entityservice.ClientService;
import by.prus.finalproject.service.entityservice.OrderService;
import by.prus.finalproject.service.entityservice.TruckService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TakeOrderCommand implements Command {

    private OrderService orderService;

    private final String ORDER_ID = "orderId";
    private final String MANAGER_ID = "manager_id";

    private final String ERROR_MESSAGE_PARAM = "errorMessage";
    private final String ERROR_MESSAGE_OTHER_MANAGER = "This order has been taken by other manager";
    private final String ALL_REQUESTS_PAGE = "WEB-INF/view/allorders.jsp";
    private final String DONE_PAGE = "WEB-INF/view/done.jsp";

    public TakeOrderCommand(OrderService orderService) { this.orderService = orderService; }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {

        try{
            HttpSession session = request.getSession();
            int manangerId = (int)session.getAttribute(MANAGER_ID);
            Manager manager = new Manager();
            manager.setIdentity(manangerId);
            int orderId = Integer.parseInt(request.getParameter(ORDER_ID));

            Order order = orderService.readById(orderId);
            if (order.getManager().getIdentity()>0){
                request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_OTHER_MANAGER);
                return CommandResult.forward(ALL_REQUESTS_PAGE);
            }

            order.setManager(manager);
            orderService.update(order);

        }catch (ClassCastException e){
            throw new ServiceException("Parsing error",e);
        }
        return CommandResult.forward(DONE_PAGE);
    }
}
