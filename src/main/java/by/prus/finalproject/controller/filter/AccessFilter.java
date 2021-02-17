package by.prus.finalproject.controller.filter;

import by.prus.finalproject.bean.Role;
import by.prus.finalproject.controller.command.implementation.GoToPageCommand;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@WebFilter(urlPatterns = { "/*" })
public class AccessFilter implements Filter {

    private static final String USER_ROLE = "userRole";
    private static final String COMMAND = "command";
    private static final int ERROR_CODE = 401;
    private List<String> managerCommands;
    private List<String> clientCommands;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        managerCommands = Arrays.asList(
                "goToNewClientPage", "newClient", "gotoManagerPage", "allClients", "gotoEditClient",
                "editClient", "deleteClient", "goToNewTruckPage", "newTruck", "truckOfManager",
                "editTruck", "deleteTruck", "gotoEditTruck", "takeOrder", "pointTruckPage", "pointTruck",
                "markAsDelivered", "truckGoToCarriage", "ordersOfManager", "ordersWithoutManager");
        clientCommands = Arrays.asList("request", "gotorequest", "order", "allOrdersForClient");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        Role role = (Role) session.getAttribute(USER_ROLE);
        String command = request.getParameter(COMMAND);

        if (role==null){
            if (managerCommands.contains(command)||clientCommands.contains(command)){
                ((HttpServletResponse) response).sendError(ERROR_CODE);
            }else{
                chain.doFilter(request,response);
            }
        }else if (role.equals(Role.MANAGER)&& clientCommands.contains(command)) {
            ((HttpServletResponse) response).sendError(ERROR_CODE);
        } else if (role.equals(Role.CLIENT)&& managerCommands.contains(command)) {
            ((HttpServletResponse) response).sendError(ERROR_CODE);
        }else {
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() { }

}
