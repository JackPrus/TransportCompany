package by.prus.finalproject.controller.command.implementation;

import by.prus.finalproject.bean.City;
import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.RequestForQuotation;
import by.prus.finalproject.controller.command.Command;
import by.prus.finalproject.controller.command.CommandResult;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.quotation.RfqService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;

public class RfqCommand implements Command {

    private final RfqService rfqService;
    private final String CITY_PICKUP = "city1";
    private final String CITY_DELIVERY = "city2";
    private final String LENGTH = "length";
    private final String WIDTH = "width";
    private final String HEIGHT = "height";
    private final String WEIGHT = "weight";
    private final String CLIENT_ID = "client_id";

    private static final String PAGE_WITH_PRICE = "WEB-INF/view/withPrice.jsp";


    public RfqCommand (RfqService rfqService){ this.rfqService=rfqService; }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ServiceException {

        try{

            String cityPickup =request.getParameter(CITY_PICKUP);
            String cityDelivery = request.getParameter(CITY_DELIVERY);
            int length = Integer.parseInt(request.getParameter(LENGTH));
            int widtg = Integer.parseInt(request.getParameter(WIDTH));
            int height = Integer.parseInt(request.getParameter(HEIGHT));
            double weight = Double.parseDouble(request.getParameter(WEIGHT));

            HttpSession session = request.getSession();
            int clientId = Integer.parseInt((String) session.getAttribute(CLIENT_ID));

            RequestForQuotation rfq = new RequestForQuotation();
            Client client = new Client();
            client.setIdentity(clientId);

            rfq.setCityPickUp(City.getCity(cityPickup));
            rfq.setCityDelivery(City.getCity(cityDelivery));
            rfq.setLength(length);
            rfq.setWidth(widtg);
            rfq.setHeight(height);
            rfq.setWeight(weight);
            rfq.setClient(client);

            request.setAttribute("price",rfqService.countPrice(rfq));

        }catch (ClassCastException e){
            throw new ServiceException("Parsing error",e);
        }

        return CommandResult.forward(PAGE_WITH_PRICE);
    }
}
