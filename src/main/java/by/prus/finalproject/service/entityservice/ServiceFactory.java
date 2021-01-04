package by.prus.finalproject.service.entityservice;

import by.prus.finalproject.exception.PersistentException;

public interface ServiceFactory {
    <Type extends Service> Type getService(Class<Type> key) throws PersistentException;

    void close();
}
