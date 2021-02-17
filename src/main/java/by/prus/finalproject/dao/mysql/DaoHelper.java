package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.dao.*;
import by.prus.finalproject.dao.pool.ConnectionPool;
import by.prus.finalproject.dao.pool.ProxyConnection;
import by.prus.finalproject.exception.PersistentException;

import java.sql.SQLException;

/**
 * Methods of current class return implementstion of DAO we need which
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class DaoHelper implements AutoCloseable {

    private final ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) throws PersistentException {
        connection = pool.getConnection();
    }

    public ManagerDao createManagerDao() { return new ManagerDaoImpl(connection); }
    public UserDao createUserDao() {return new UserDaoImpl(connection);}
    public ClientDao createClientDao(){return new ClientDaoImpl(connection);}
    public OrderDao createOrderDao(){return new OrderDaoImpl(connection);}
    public TruckDao createTruckDao(){return new TruckDaoImpl(connection);}

    public void close() { connection.close(); }

    public void startTransacation() throws PersistentException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

}
