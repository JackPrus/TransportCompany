package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.bean.Order;
import by.prus.finalproject.bean.Truck;
import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.entityservice.OrderService;
import by.prus.finalproject.service.entityservice.TruckService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * In case Manager just have know that some order has been delivered by truck
 * he can mark this order as 'delivered' in CRM system.
 * If truck don't have any current orders truck is mrked as free. (field truck.isBusy set 'false')
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class DeliveredCommand implements Command {

    private OrderService orderService;
    private TruckService truckService;

    private final String ORDER_ID = "orderId";
    private final String TRUCK_ID = "truckId";

    private final String ERROR_MESSAGE_PARAM = "errorMessage";
    private final String NO_TRUCKS_FOR_ORDER = "No_truck_for_order";

    private final String ORDERS_OF_MANAGER_PAGE = "WEB-INF/view/ordersOfManager.jsp";
    private final String MY_ORDERS_PAGE = "WEB-INF/view/ordersOfManager.jsp";

    public DeliveredCommand(OrderService orderService, TruckService truckService) {
        this.orderService = orderService;
        this.truckService = truckService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {

        try{
            int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
            int truckId = Integer.parseInt(request.getParameter(TRUCK_ID));

            if (truckId!=0){
                Order order = orderService.readById(orderId);
                order.setActive(false);
                orderService.update(order);
            }else{
                // the page will be updated and user see error message
                request.setAttribute(ERROR_MESSAGE_PARAM, NO_TRUCKS_FOR_ORDER);
                OrdersOfManagerCommand ordersOfManagerCommand = new OrdersOfManagerCommand(orderService);
                return ordersOfManagerCommand.execute(request,response);
            }

            Truck truck = truckService.read(truckId);
            List<Order> currentOrdersOfTruck = orderService.getCurrentOrdersOfTruck(truck);

            if (currentOrdersOfTruck.size()==0){
                truck.setBusy(false);
                truckService.updateTruck(truck);
            }

        }catch (ClassCastException e){
            throw new ServiceException(e);
        }catch (NumberFormatException e){
            request.setAttribute(ERROR_MESSAGE_PARAM, NO_TRUCKS_FOR_ORDER);
            return CommandResult.forward(ORDERS_OF_MANAGER_PAGE);
        }

        OrdersOfManagerCommand ordersOfManager = new OrdersOfManagerCommand(orderService);
        return ordersOfManager.execute(request,response);
    }
}
