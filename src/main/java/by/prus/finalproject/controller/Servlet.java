package by.prus.finalproject.controller;

//import by.prus.finalproject.controller.command.Command;
//import by.prus.finalproject.controller.command.CommandFactory;
//import by.prus.finalproject.controller.command.CommandResult;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class Servlet extends HttpServlet {
//
//    private static final String COMMAND = "command";
//    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
//    private static final String ERROR_MESSAGE_PARAM = "errorMessage";
//    private static final Logger LOGGER = LogManager.getLogger(Controller.class);
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        process(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        process(request, response);
//    }
//
//    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            String commandRequest = request.getParameter(COMMAND);
//            Command action = CommandFactory.create(commandRequest);
//            CommandResult commandResult = action.execute(request, response);
//            String page = commandResult.getPage();
//            if (commandResult.getIsRedirect()) {
//                response.sendRedirect(page);
//            } else {
//                forwardPage(page, request, response);
//            }
//        }
//        /*catch (PageNotFoundException e) {
//            response.sendError(404);
//        } */ catch (Exception e) {
//
//            LOGGER.error(e.getMessage(), e);
//            request.setAttribute(ERROR_MESSAGE_PARAM, e.getMessage()); //TODO change error message and page
//            forwardPage(ERROR_PAGE, request, response);
//        }
//
//    }
//
//    private void forwardPage(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
//        dispatcher.forward(request, response);
//    }
//
//}
