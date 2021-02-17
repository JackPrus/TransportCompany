package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.bean.Order;
import by.prus.finalproject.bean.Truck;
import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.dao.mysql.DaoHelperFactory;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.entityservice.OrderService;
import by.prus.finalproject.service.entityservice.TruckService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Manager can send truck to carriage when decide that
 * truck has already filled with orders
 * Manager will see an error if try to send truck not having orders
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class TruckToCarriageCommand implements Command {

    private TruckService truckService;
    private OrderService orderService;

    private final String TRUCK_ID = "truckId";

    private final String ERROR_MESSAGE_PARAM = "errorMessage";
    private final String ERROR_MESSAGE_WRONG_DATA = "wrong_data";

    private final String MY_TRUCKS_PAGE = "WEB-INF/view/myTrucks.jsp";

    public TruckToCarriageCommand(TruckService truckService, OrderService orderService) {
        this.truckService = truckService;
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {

        try{

            int truckId = Integer.parseInt(request.getParameter(TRUCK_ID));
            Truck truck = truckService.read(truckId);

            List<Order> ordersOfTruck = orderService.getCurrentOrdersOfTruck(truck);

            if (ordersOfTruck.size()==0){
                request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_DATA);
                return CommandResult.forward(MY_TRUCKS_PAGE);
            }

            truck.setBusy(true);
            truckService.updateTruck(truck);

        }catch (ClassCastException e){
            throw new ServiceException(e);
        }catch (NumberFormatException e){
            request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_DATA);
            return CommandResult.forward(MY_TRUCKS_PAGE);
        }

        TruckOfManagerCommand truckOfManagerCommand = new TruckOfManagerCommand(truckService);
        return truckOfManagerCommand.execute(request,response);
    }
}
