package by.prus.finalproject.service.mapper;

import by.prus.finalproject.exception.PersistentException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T map(ResultSet resultSet) throws PersistentException;
}
