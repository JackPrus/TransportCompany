package by.prus.finalproject.dao.pool;

import by.prus.finalproject.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Wrapper of connection. Class represent pool of connections and when initializotion going on
 * @see ConnectionFactory creates connections (POOL_SIZE) and put them into availableConnections.
 * In order to get this connection service Daohelper get one of connection from availableConnections.
 * @see by.prus.finalproject.dao.mysql.DaoHelper
 * And this connection replace from availableConnections to connectionsInUse.
 * When job done, method turnBackConnection replace this connection from connectionsInUse
 * back to availableConnections.
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class ConnectionPool {

    private static Logger logger = LogManager.getLogger(ConnectionPool.class);

    private static ConnectionPool instance = null;
    private static AtomicBoolean initStarted = new AtomicBoolean(instance == null);
    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionsInUse;
    private static final ReentrantLock CONNECTIONS_LOCKER = new ReentrantLock();
    private static final ReentrantLock INSTANCE_LOCKER = new ReentrantLock();
    private final Semaphore semaphore = new Semaphore(POOL_SIZE, true);
    private static final int POOL_SIZE = 10;
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    private ConnectionPool() {
        availableConnections = new ArrayDeque<>();
        connectionsInUse = new ArrayDeque<>();
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = connectionFactory.create();
            ProxyConnection proxyConnection = new ProxyConnection(connection, this);
            availableConnections.add(proxyConnection);
        }
    }

    public static ConnectionPool getInstance() {        // TODO improve
        ConnectionPool localInstance = instance;
        if (initStarted.get()) {
            INSTANCE_LOCKER.lock();
            try {
                localInstance = instance;
                if (initStarted.get()) {
                    instance = new ConnectionPool();
                }
            } finally {
                INSTANCE_LOCKER.unlock();
            }
        }
        return instance;
    }

    public void turnBackConnection(ProxyConnection proxyConnection) {
        semaphore.release();
        CONNECTIONS_LOCKER.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                connectionsInUse.remove(proxyConnection);
                availableConnections.offer(proxyConnection);
            }
        } finally {
            CONNECTIONS_LOCKER.unlock();
        }
    }

    public ProxyConnection getConnection() throws PersistentException {
        try {
            semaphore.acquire();
            CONNECTIONS_LOCKER.lock();
            ProxyConnection proxyConnection = availableConnections.poll();
            connectionsInUse.offer(proxyConnection);
            return proxyConnection;
        } catch (InterruptedException e) {
            throw new PersistentException(e);
        } finally {
            CONNECTIONS_LOCKER.unlock();
        }
    }

    public void closeAllConnections() throws SQLException {
        for (Connection connection : connectionsInUse) {
            connection.close();
        }
        for (ProxyConnection connection : availableConnections) {
            connection.killConnection();
        }
    }

}
