package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.bean.Manager;
import by.prus.finalproject.bean.Truck;
import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.entityservice.TruckService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Command need to see all truck manager responsible for.
 * He can rool these trucks he can add, update or delete truck of his list.
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class TruckOfManagerCommand implements Command {

    private TruckService truckService;

    private static final String MANAGER_ID = "manager_id";
    private static final String TRUCKS_OF_MANAGER = "allTrucks";
    private static final String TRUCKS_OF_MANAGER_PAGE = "WEB-INF/view/myTrucks.jsp";


    public TruckOfManagerCommand(TruckService truckService) {
        this.truckService = truckService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {

        try{
            HttpSession session = request.getSession();
            int managerId = (int) session.getAttribute(MANAGER_ID);

            List<Truck> truckList = truckService.readByManagerId(managerId);
            session.setAttribute(TRUCKS_OF_MANAGER, truckList);

        }catch (ClassCastException e){
            throw new ServiceException(e);
        }

        return CommandResult.forward(TRUCKS_OF_MANAGER_PAGE);
    }
}
