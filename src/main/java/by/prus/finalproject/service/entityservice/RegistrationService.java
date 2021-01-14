package by.prus.finalproject.service.entityservice;

import by.prus.finalproject.bean.*;
import by.prus.finalproject.dao.Dao;
import by.prus.finalproject.dao.mysql.DaoHelper;
import by.prus.finalproject.dao.mysql.DaoHelperFactory;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;

public class RegistrationService {

    private final DaoHelperFactory daoHelperFactory;

    public RegistrationService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public User registration (String login, String password1, String password2, String email, ClientType clientType, String name) throws PersistentException, ServiceException {
        Role role = Role.CLIENT;
        LoginService loginService = new LoginService(daoHelperFactory);
        if (loginService.login(login,password1)==null){
            try(DaoHelper daoHelper = daoHelperFactory.create();){
                Dao dao = daoHelper.createClientDao();
                Client client = new Client(); // ВСТАВИТЬ проверку на соответствие логина и пароля !!!!
                client.setName(name);
                client.setClientType(clientType);
                dao.create(client);
                return loginService.login(login,password1);
            }


        }

        return  null;
    }

}
