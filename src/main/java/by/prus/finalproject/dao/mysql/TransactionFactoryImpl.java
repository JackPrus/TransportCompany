package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.dao.Transaction;
import by.prus.finalproject.dao.TransactionFactory;
import by.prus.finalproject.dao.pool.ConnectionPool;
import by.prus.finalproject.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl implements TransactionFactory {

    private static final Logger logger = LogManager.getLogger(TransactionFactoryImpl.class);

    private Connection connection;

    public TransactionFactoryImpl() throws PersistentException {
        connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch(SQLException e) {
            logger.error("It is impossible to turn off autocommiting for database connection", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public Transaction createTransaction() throws PersistentException {
        return new TransactionImpl(connection);
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch(SQLException e) {}
    }

}
