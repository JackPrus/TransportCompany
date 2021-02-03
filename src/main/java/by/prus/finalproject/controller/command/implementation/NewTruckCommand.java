package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.bean.Manager;
import by.prus.finalproject.bean.Truck;
import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.entityservice.TruckService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

public class NewTruckCommand implements Command {

    private TruckService truckService;

    private final String TRUCK_NO = "truckNo";
    private final String LENGTH_CAPACITY = "lengthCapacity";
    private final String WIDTH_CAPACITY = "widthCapacity";
    private final String HEIGHT_CAPACITY = "heightCapacity";
    private final String WEIGHT_CAPACITY = "weightCapacity";

    private final String MANAGER_ID = "manager_id";
    private final String IS_BUSY = "isbusy";


    private final String ERROR_MESSAGE_PARAM = "errorMessage";
    private final String ERROR_MESSAGE_WRONG_DATA = "wrong_data";


    private final String NEW_TRUCK_PAGE = "WEB-INF/view/newTruck.jsp";
    private final String DONE_PAGE = "WEB-INF/view/done.jsp";

    public NewTruckCommand(TruckService truckService) { this.truckService = truckService; }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {

        Truck truck = null;

        try{

            HttpSession session = request.getSession();
            int managerId = (int) session.getAttribute(MANAGER_ID);
            Manager manager = new Manager();
            manager.setIdentity(managerId);

            String truckNo = request.getParameter(TRUCK_NO);
            int length = Integer.parseInt(request.getParameter(LENGTH_CAPACITY));
            int width = Integer.parseInt(request.getParameter(WIDTH_CAPACITY));
            int height = Integer.parseInt(request.getParameter(HEIGHT_CAPACITY));
            int weight = Integer.parseInt(request.getParameter(WEIGHT_CAPACITY));

            if (!truckService.isCorrectData(truckNo,length,width,height,weight)){
                request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_DATA);
                return CommandResult.forward(NEW_TRUCK_PAGE);
            }

            truck = new Truck();
            truck.setTruckNo(truckNo);
            truck.setLengthCapacity(length);
            truck.setWidthCapacity(width);
            truck.setHeightCapacity(height);
            truck.setWeightCapacity(weight);
            truck.setBusy(false);
            truck.setManager(manager);

        }catch (ClassCastException e){
            throw new ServiceException(e);
        }catch (NumberFormatException e){
            request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_DATA);
            return CommandResult.forward(NEW_TRUCK_PAGE);
        }

        truckService.createTruck(truck);

        return CommandResult.forward(DONE_PAGE);
    }


}
