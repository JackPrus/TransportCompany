package by.prus.finalproject.controller.command;


import by.prus.finalproject.bean.Truck;
import by.prus.finalproject.controller.command.implementation.*;
import by.prus.finalproject.dao.mysql.DaoHelperFactory;
import by.prus.finalproject.service.entityservice.ClientService;
import by.prus.finalproject.service.entityservice.LoginService;
import by.prus.finalproject.service.entityservice.OrderService;
import by.prus.finalproject.service.entityservice.TruckService;
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
    private static final String GO_TO_EDIT_CLIENT = "gotoEditClient";
    private static final String EDIT_CLIENT= "editClient";
    private static final String DELETE_CLIENT = "deleteClient";
    private static final String GO_TO_NEW_TRUCK_PAGE = "goToNewTruckPage";
    private static final String NEW_TRUCK = "newTruck";
    private static final String TRUCKS_OF_MANAGER = "truckOfManager";
    private static final String EDIT_TRUCK = "editTruck";  //editTruck
    private static final String DELETE_TRUCK = "deleteTruck";
    private static final String GO_TO_EDIT_TRUCK = "gotoEditTruck";
    private static final String TAKE_ORDER = "takeOrder";
    private static final String POINT_TRUCK_PAGE = "pointTruckPage";
    private static final String POINT_TRUCK = "pointTruck";
    private static final String DELIVERED = "markAsDelivered";

    //deleteTruck

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
            case EDIT_CLIENT:
                return new EditClientCommand(new ClientService(new DaoHelperFactory()));
            case DELETE_CLIENT:
                return new DeleteClientCommand(new ClientService(new DaoHelperFactory()));
            case NEW_TRUCK:
                return new NewTruckCommand(new TruckService(new DaoHelperFactory()));
            case TRUCKS_OF_MANAGER:
                return new TruckOfManagerCommand(new TruckService(new DaoHelperFactory()));
            case EDIT_TRUCK:
                return new EditTruckCommand(new TruckService(new DaoHelperFactory()));
            case DELETE_TRUCK:
                return new DeleteTruckCommand(new TruckService(new DaoHelperFactory()));
            case TAKE_ORDER:
                return new TakeOrderCommand(new OrderService(new DaoHelperFactory()));
            case POINT_TRUCK:
                return new PointTruckForOrderCommand(new OrderService(new DaoHelperFactory()));
            case POINT_TRUCK_PAGE:
                return new TruckOfManagerCommand(new TruckService(new DaoHelperFactory()));
            case DELIVERED:
                return new DeliveredCommand(new OrderService(new DaoHelperFactory()), new TruckService(new DaoHelperFactory()));


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
            case GO_TO_EDIT_CLIENT:
                return new GoToPageCommand("WEB-INF/view/editClient.jsp");
            case GO_TO_NEW_TRUCK_PAGE:
                return new GoToPageCommand("WEB-INF/view/newTruck.jsp");
            case GO_TO_EDIT_TRUCK:
                return new GoToPageCommand("WEB-INF/view/editTruck.jsp");
            default:
                throw new IllegalArgumentException("illegal argument"); // need login abd error page
        }
    }
}
