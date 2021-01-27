package by.prus.finalproject.service.entityservice;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.dao.OrderDao;
import by.prus.finalproject.dao.mysql.DaoHelper;
import by.prus.finalproject.dao.mysql.DaoHelperFactory;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;

import java.util.List;

public class OrderService {

    private final DaoHelperFactory daoHelperFactory;

    public OrderService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void createNewOrder(Order order) throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.create()) {
            OrderDao dao = daoHelper.createOrderDao();
            dao.create(order);
        } catch (PersistentException e) {
            throw new ServiceException(e);
        }
    }

    public List<Order> getOrdersForClient (Client client) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()){
            OrderDao dao = daoHelper.createOrderDao();
            return dao.getOrdersByClient(client);
        }catch (PersistentException e){
            throw new ServiceException(e);
        }
    }

}
