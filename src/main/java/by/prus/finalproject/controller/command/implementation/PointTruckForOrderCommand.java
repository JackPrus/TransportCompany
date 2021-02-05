package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.bean.Order;
import by.prus.finalproject.bean.Truck;
import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.entityservice.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PointTruckForOrderCommand implements Command {

    private OrderService orderService;

    private final String ORDER_ID = "orderId";
    private final String TRUCK_ID = "truckId";

    private final String ERROR_MESSAGE_PARAM = "errorMessage";
    private final String ERROR_MESSAGE_WRONG_DATA = "wrong_data";

    private final String ORDERS_OF_MANAGER_PAGE = "WEB-INF/view/ordersOfManager.jsp";
    private final String MY_TRUCK_PAGE = "WEB-INF/view/myTrucks.jsp";

    public PointTruckForOrderCommand(OrderService orderService) { this.orderService = orderService; }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {
        try{
            int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
            int truckId = Integer.parseInt(request.getParameter(TRUCK_ID));

            Truck truck = new Truck();
            truck.setIdentity(truckId);

            Order order = orderService.readById(orderId);
            order.setTruck(truck);

            orderService.update(order);

        }catch (ClassCastException e){
            throw new ServiceException(e);
        }catch (NumberFormatException e){
            request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_DATA);
            return CommandResult.forward(MY_TRUCK_PAGE);
        }

        //in order to update all orders page (button 'point truck for order' will disapear
        // if truck has been pointd)
        OrdersOfManagerCommand ordersOfManager = new OrdersOfManagerCommand(orderService);

        return ordersOfManager.execute(request,response);

    }


}
