package by.prus.finalproject.controller.command;


import by.prus.finalproject.controller.command.implementation.*;
import by.prus.finalproject.dao.mysql.DaoHelperFactory;
import by.prus.finalproject.service.entityservice.ClientService;
import by.prus.finalproject.service.entityservice.LoginService;
import by.prus.finalproject.service.entityservice.OrderService;
import by.prus.finalproject.service.quotation.RfqService;

public class CommandFactory {
    private static final String COMMAND_LOCALIZATION = "locale";
    private static final String AUTHORIZATION_COMMAND = "authorization";
    private static final String LOGIN_COMMAND = "login";
    private static final String RFQ = "request";
    private static final String LOGOUT_COMMAND = "logout";
    private static final String GO_BACK_TO_REQUEST = "gotorequest";
    private static final String NEW_ORDER = "order";
    private static final String GO_TO_NEW_CLIENT = "goToNewClientPage";
    private static final String NEW_CLIENT = "newClient";
    private static final String ALLORDERS_FOR_CLIENT = "allOrdersForClient";
    private static final String GO_TO_MAGAGER_PAGE = "gotoManagerPage";
    private static final String ALL_CLIENTS = "allClients";

    private static final String CONTACTS_PAGE = "contactsPage";
    private static final String ABOUT_US_PAGE = "aboutUsPage";
    private static final String MAIN_PAGE = "mainPage";
    private static final String ORDERS_OF_MANAGER = "ordersOfManager";
    private static final String ORDERS_WITHOUT_MAGAGER = "ordersWithoutManager";


    public static Command create(String command) {
        switch (command) {
            case COMMAND_LOCALIZATION:
                return new LocalizationCommand();
            case LOGIN_COMMAND:
                return new LoginCommand(new LoginService(new DaoHelperFactory()));
            case RFQ:
                return new RfqCommand(new RfqService());
            case LOGOUT_COMMAND:
                return new LogOutCommand();
            case NEW_ORDER:
                return new CreateNewOrderCommand(new OrderService(new DaoHelperFactory()));
            case ALLORDERS_FOR_CLIENT:
                return new GetClientsOrdersCommand(new OrderService(new DaoHelperFactory()));
            case ORDERS_OF_MANAGER:
                return new OrdersOfManagerCommand(new OrderService(new DaoHelperFactory()));
            case ORDERS_WITHOUT_MAGAGER:
                return new OrdersWithoutManagerCommand(new OrderService(new DaoHelperFactory()));
            case NEW_CLIENT:
                return new NewClientCommand(new ClientService(new DaoHelperFactory()), new LoginService(new DaoHelperFactory()));
            case ALL_CLIENTS:
                return new AllClietnsCommand(new ClientService(new DaoHelperFactory()));


            case GO_BACK_TO_REQUEST:
                return new GoToPageCommand("WEB-INF/view/userPage.jsp");
            case AUTHORIZATION_COMMAND:
                return new GoToPageCommand("WEB-INF/view/login.jsp");
            case CONTACTS_PAGE:
                return new GoToPageCommand("WEB-INF/view/contacts.jsp");
            case ABOUT_US_PAGE:
                return new GoToPageCommand("WEB-INF/view/aboutUs.jsp");
            case MAIN_PAGE :
                return new GoToPageCommand("WEB-INF/view/main.jsp");
            case GO_TO_NEW_CLIENT:
                return new GoToPageCommand("WEB-INF/view/newClient.jsp");
            case GO_TO_MAGAGER_PAGE:
                return new GoToPageCommand("WEB-INF/view/managerPage.jsp");
            default:
                throw new IllegalArgumentException("illegal argument"); // need login abd error page
        }
    }
}
