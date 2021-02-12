package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.bean.City;
import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.bean.RequestForQuotation;
import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.entityservice.OrderService;
import by.prus.finalproject.service.quotation.RfqService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Class create new Order. This command invoked by Client only.
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class CreateNewOrderCommand implements Command {

    private final OrderService orderService;

    /**
     * CITY_PICKUP - city of loading
     * CITY_DELIVERY - city where should be unloading
     * LENGTH - length of cargo (cm)
     * WIDTH - width of cargo (cm)
     * HEIGHT - height of cargo (cm)
     * WEIGHT - weight of cargo (cm)
     * CLIENT_ID - id of current client sending this order
     * PICKUP_ADDRESS - addres of pickup (not necessary)
     * DELIVERY_ADDRESS - addres of delivery (not necessary)
     * PRICE - price of order
     */
    private final String CITY_PICKUP = "city1";
    private final String CITY_DELIVERY = "city2";
    private final String LENGTH = "length";
    private final String WIDTH = "width";
    private final String HEIGHT = "height";
    private final String WEIGHT = "weight";
    private final String CLIENT_ID = "client_id";
    private final String PICKUP_ADDRESS = "adressPickUp";
    private final String DELIVERY_ADDRESS = "adressDelivery";
    private final String PRICE = "price";

    private final String ERROR_MESSAGE_PARAM = "errorMessage";
    private final String ERROR_MESSAGE_WRONG_DATA = "wrong_info";

    private static final String ORDER_DONE_PAGE = "WEB-INF/view/done.jsp";
    private static final String USER_PAGE = "WEB-INF/view/userPage.jsp";

    public CreateNewOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {

        try{

            String cityPickup = request.getParameter(CITY_PICKUP);
            String cityDelivery = request.getParameter(CITY_DELIVERY);
            int length = Integer.parseInt(request.getParameter(LENGTH));
            int widtg = Integer.parseInt(request.getParameter(WIDTH));
            int height = Integer.parseInt(request.getParameter(HEIGHT));
            double weight = Double.parseDouble(request.getParameter(WEIGHT));
            String pickUpAddress = request.getParameter(PICKUP_ADDRESS);
            String deliveryAddress = request.getParameter(DELIVERY_ADDRESS);
            String price = request.getParameter(PRICE);

            HttpSession session = request.getSession();
            Integer clientId = (Integer) session.getAttribute(CLIENT_ID);

            Client client = new Client();
            client.setIdentity(clientId);

            Order order = new Order();
            order.setCityPickUp(City.getCity(cityPickup));
            order.setCityDelivery(City.getCity(cityDelivery));
            order.setClient(client);
            order.setPrice(new BigDecimal(price));
            order.setActive(true);
            order.setOrderDate(new Date());
            order.setLength(length);
            order.setWidth(widtg);
            order.setHeight(height);
            order.setWeight(weight);
            order.setPickupAdress(pickUpAddress);
            order.setUnloadingAdress(deliveryAddress);

            //manager set when take it , truck will be pointed by manager
            //order is not active (false) when it delivered

            orderService.createNewOrder(order);

        }catch (ClassCastException e){
            throw new ServiceException("Parsing error",e);
        }catch (NumberFormatException e){
            request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_DATA);
            return CommandResult.forward(USER_PAGE);
        }

        return CommandResult.forward(ORDER_DONE_PAGE);
    }
}
