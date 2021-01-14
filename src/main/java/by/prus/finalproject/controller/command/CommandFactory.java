package by.prus.finalproject.controller.command;

import by.prus.finalproject.controller.command.implementation.LocalizationCommand;
import by.prus.finalproject.controller.command.implementation.LoginCommand;
import by.prus.finalproject.dao.mysql.DaoHelperFactory;
import by.prus.finalproject.service.entityservice.LoginService;

public class   CommandFactory {
//    private static final String COMMAND_LOCALIZATION = "locale";
//    private static final String AUTHORIZATION_COMMAND = "authorization";
    private static final String LOGIN_COMMAND = "login";
//    private static final String LOGOUT_COMMAND = "logout";
//    private static final String APPLICATION_HISTORY_COMMAND = "applicationHistory";
//    private static final String CONTACTS_PAGE = "contacts";
//    private static final String ABOUT_US_PAGE = "aboutUs";


    public static Command create(String command) {
        switch (command) {
//            case COMMAND_LOCALIZATION:
//                return new LocalizationCommand();
            case LOGIN_COMMAND:
                return new LoginCommand(new LoginService(new DaoHelperFactory()));
//            case LOGOUT_COMMAND:
//                return new LogOutCommand();
//            case BOOKING_COMMAND:
//                return new BookingCommand(new BookingService(new DaoHelperFactory()), new ApplicationCreator());
//            case APPLICATION_HISTORY_COMMAND:
//                return new ApplicationHistoryCommand(new ApplicationHistoryService(new DaoHelperFactory()));
//
//            case AUTHORIZATION_COMMAND:
//                return new GoToPageCommand("WEB-INF/view/login.jsp");
//            case CONTACTS_PAGE:
//                return new GoToPageCommand("WEB-INF/view/contacts.jsp");
//            case ABOUT_US_PAGE:
//                return new GoToPageCommand("WEB-INF/view/aboutUs.jsp");

            default:
                throw new IllegalArgumentException("illegal argument"); // need login abd error page
        }
    }
}
