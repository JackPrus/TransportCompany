package by.prus.finalproject.service.user;

public class LoginSevice {

    // Демонстрационная модель. Должен быть интерфейс и тд.

    public boolean login (String user, String password){
        return "admin".equals(user)&& "admin".equals(password);
    }

}
