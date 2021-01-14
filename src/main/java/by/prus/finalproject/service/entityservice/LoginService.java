package by.prus.finalproject.service.entityservice;

import by.prus.finalproject.bean.User;
import by.prus.finalproject.dao.UserDao;
import by.prus.finalproject.dao.mysql.DaoHelper;
import by.prus.finalproject.dao.mysql.DaoHelperFactory;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;

import java.util.Optional;

public class LoginService {
    private final DaoHelperFactory daoHelperFactory;

    public LoginService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public User login(final String login, final String password) throws PersistentException, ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.create()){
            UserDao dao = daoHelper.createUserDao();
            return dao.findUserByLoginAndPassword(login,password);
        }
        catch (PersistentException e){
            throw new ServiceException(e);
        }
    }

}
