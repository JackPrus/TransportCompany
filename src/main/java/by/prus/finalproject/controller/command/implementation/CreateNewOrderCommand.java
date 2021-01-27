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

public class CreateNewOrderCommand implements Command {

    private final OrderService orderService;

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

    private static final String ORDER_DONE_PAGE = "WEB-INF/view/orderdone.jsp";

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
        }

        return CommandResult.forward(ORDER_DONE_PAGE);
    }
}
