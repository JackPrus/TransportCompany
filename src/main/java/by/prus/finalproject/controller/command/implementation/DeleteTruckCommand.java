package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.entityservice.TruckService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTruckCommand implements Command {

    private TruckService truckService;

    private final String ERROR_MESSAGE_PARAM = "errorMessage";
    private final String ERROR_MESSAGE_WRONG_DATA = "wrong_data";
    private final String EDIT_TRUCK_PAGE = "WEB-INF/view/editTruck.jsp";

    private final String TRUCK_ID = "truckId";
    private final String DONE_PAGE = "WEB-INF/view/done.jsp";

    public DeleteTruckCommand(TruckService truckService) { this.truckService = truckService; }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {

        try{
            int truckId = Integer.parseInt(request.getParameter(TRUCK_ID));
            truckService.deleteTruck(truckId);
            return CommandResult.forward(DONE_PAGE);
        }catch (ClassCastException e){
            throw new ServiceException(e);
        }catch (NumberFormatException e){
            request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_DATA);
            return CommandResult.forward(EDIT_TRUCK_PAGE);
        }

    }
}
