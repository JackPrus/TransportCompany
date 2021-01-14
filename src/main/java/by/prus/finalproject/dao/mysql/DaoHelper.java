package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.dao.ClientDao;
import by.prus.finalproject.dao.ManagerDao;
import by.prus.finalproject.dao.UserDao;
import by.prus.finalproject.dao.pool.ConnectionPool;
import by.prus.finalproject.dao.pool.ProxyConnection;
import by.prus.finalproject.exception.PersistentException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private final ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) throws PersistentException {
        connection = pool.getConnection();
    }

    public ManagerDao createManagerDao() { return new ManagerDaoImpl(connection); }
    public UserDao createUserDao() {return new UserDaoImpl2(connection);}
    public ClientDao createClientDao(){return new ClientDaoImpl(connection);}

    public void close() {
        connection.close();
    }

    public void startTransacation() throws PersistentException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

}
