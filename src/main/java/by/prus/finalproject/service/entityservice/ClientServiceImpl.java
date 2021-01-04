package by.prus.finalproject.service.entityservice;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.dao.ClientDao;
import by.prus.finalproject.dao.mysql.ClientDaoImpl;
import by.prus.finalproject.exception.PersistentException;

import java.util.List;

public class ClientServiceImpl extends ServiceImpl implements ClientService {
    @Override
    public List<Client> findAll() throws PersistentException {
        ClientDao dao = transaction.createDao(ClientDaoImpl.class);
        return dao.readAll();
    }

    @Override
    public List<Client> findByName(String name) throws PersistentException {
        return null;
    }

    @Override
    public Client findByOrder(Order order) throws PersistentException {
        return null;
    }

    @Override
    public void save(Client client) throws PersistentException {

    }

    @Override
    public void delete(Integer id) throws PersistentException {

    }
}
