package by.prus.finalproject.service.entityservice;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.User;
import by.prus.finalproject.dao.ClientDao;
import by.prus.finalproject.dao.UserDao;
import by.prus.finalproject.dao.mysql.DaoHelper;
import by.prus.finalproject.dao.mysql.DaoHelperFactory;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;

import java.rmi.server.ServerCloneException;
import java.util.List;

public class ClientService {

    private final DaoHelperFactory daoHelperFactory;

    public ClientService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }


    public void createUser(User user) throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.create()){
            UserDao dao = daoHelper.createUserDao();
            dao.create(user);
        }catch (PersistentException e){
            throw new ServiceException(e);
        }
    }

    public int createClient(Client client) throws ServiceException { //return id and than use it in User
        try(DaoHelper daoHelper = daoHelperFactory.create()){
            ClientDao dao = daoHelper.createClientDao();
            return dao.create(client);

        }catch (PersistentException e){
            throw new ServiceException(e);
        }
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

    public List<Client> readAllClients() throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.create()){
            ClientDao dao = daoHelper.createClientDao();
            return dao.readAll();
        }
        catch (PersistentException e){
            throw new ServiceException(e);
        }
    }

}
