package by.prus.finalproject.service.entityservice;

import by.prus.finalproject.dao.Transaction;

public abstract class ServiceImpl {
    protected Transaction transaction = null;
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
