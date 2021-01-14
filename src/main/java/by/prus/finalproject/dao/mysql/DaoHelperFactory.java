package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.dao.pool.ConnectionPool;
import by.prus.finalproject.exception.PersistentException;

public class DaoHelperFactory {
    public DaoHelper create() throws PersistentException {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
