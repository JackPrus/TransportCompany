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
/**
 * Manager can edit any truck in case information about truck has been changed.
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class EditTruckCommand implements Command {

    private TruckService truckService;

    private final String TRUCK_ID = "truckId";
    private final String TRUCK_NO = "truckNo";
    private final String LENGTH_CAPACITY = "lengthCapacity";
    private final String WIDTH_CAPACITY = "widthCapacity";
    private final String HEIGHT_CAPACITY = "heightCapacity";
    private final String WEIGHT_CAPACITY = "weightCapacity";


    private final String ERROR_MESSAGE_PARAM = "errorMessage";
    private final String ERROR_MESSAGE_WRONG_DATA = "wrong_data";
    private final String MANAGER_ID = "managerId";


    private final String EDIT_TRUCK_PAGE = "WEB-INF/view/editTruck.jsp";
    private final String DONE_PAGE = "WEB-INF/view/done.jsp";


    public EditTruckCommand(TruckService truckService) {
        this.truckService = truckService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {

        Truck truck = null;

        try{

            int managerId = Integer.parseInt(request.getParameter(MANAGER_ID));
            Manager manager = new Manager();
            manager.setIdentity(managerId);

            int identy = Integer.parseInt(request.getParameter(TRUCK_ID));
            String truckNo = request.getParameter(TRUCK_NO);
            int length = Integer.parseInt(request.getParameter(LENGTH_CAPACITY));
            int width = Integer.parseInt(request.getParameter(WIDTH_CAPACITY));
            int height = Integer.parseInt(request.getParameter(HEIGHT_CAPACITY));
            int weight = Integer.parseInt(request.getParameter(WEIGHT_CAPACITY));

            if (!truckService.isCorrectData(truckNo,length,width,height,weight)){
                request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_DATA);
                return CommandResult.forward(EDIT_TRUCK_PAGE);
            }

            truck = new Truck();
            truck.setIdentity(identy);
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
            return CommandResult.forward(EDIT_TRUCK_PAGE);
        }

        truckService.updateTruck(truck);

        return CommandResult.forward(DONE_PAGE);
    }
}
