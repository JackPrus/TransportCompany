package by.prus.finalproject.controller.command;


import by.prus.finalproject.controller.command.implementation.*;
import by.prus.finalproject.dao.mysql.DaoHelperFactory;
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
    private static final String ALLORDERS_FOR_CLIENT = "allOrdersForClient";

    private static final String CONTACTS_PAGE = "contactsPage";
    private static final String ABOUT_US_PAGE = "aboutUsPage";
    private static final String MAIN_PAGE = "mainPage";


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
            default:
                throw new IllegalArgumentException("illegal argument"); // need login abd error page
        }
    }
}
