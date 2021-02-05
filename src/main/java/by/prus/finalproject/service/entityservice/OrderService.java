package by.prus.finalproject.service.entityservice;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.Manager;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.bean.Truck;
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

    public List<Order>getOrdersOfManager (Manager manager) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()){
            OrderDao dao = daoHelper.createOrderDao();
            return dao.getOrdersOfManager(manager);
        }catch (PersistentException e){
            throw new ServiceException(e);
        }
    }

    public List<Order>getOrdersWithoutManager () throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()){
            OrderDao dao = daoHelper.createOrderDao();
            return dao.getOrdersWithoutManager();
        }catch (PersistentException e){
            throw new ServiceException(e);
        }
    }

    public Order readById (int identity) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()){
            OrderDao dao = daoHelper.createOrderDao();
            return dao.read(identity);
        }catch (PersistentException e){
            throw new ServiceException(e);
        }
    }

    public void update(Order order) throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.create()) {
            OrderDao dao = daoHelper.createOrderDao();
            dao.update(order);
        } catch (PersistentException e) {
            throw new ServiceException(e);
        }
    }

    public List<Order> getOrdersOfTruck(Truck truck) throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.create()) {
            OrderDao dao = daoHelper.createOrderDao();
            return dao.getOrdersOfTruck(truck);
        } catch (PersistentException e) {
            throw new ServiceException(e);
        }
    }

}
