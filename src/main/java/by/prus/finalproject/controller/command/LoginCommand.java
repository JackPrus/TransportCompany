package by.prus.finalproject.controller.command;

import by.prus.finalproject.service.user.LoginSevice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String loging = request.getParameter("login");
        String password = request.getParameter("password");

        LoginSevice loginSevice = new LoginSevice();
        boolean valid = loginSevice.login(loging,password);

        if (valid){
            return "WEB-INF/view/main.jsp"; // если валидный - направляем на основную Jsp
        }else {
            request.setAttribute("errorMessage", "invalid creds");
            return "index.jsp"; // если нет -
        }


    }
}
