package by.prus.finalproject.dao;

import by.prus.finalproject.exception.PersistentException;

public interface TransactionFactory {
    Transaction createTransaction() throws PersistentException;

    void close();
}
