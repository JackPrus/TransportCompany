package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.bean.Entity;
import by.prus.finalproject.dao.Dao;
import by.prus.finalproject.exception.PersistentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

//public abstract class AbstractDao implements Dao {
//
//    private static final String GET_ALL = "SELECT * FROM ";
//    private static final String WHERE_ID = " WHERE id = ?";
//    private static final String DELETE_FROM = "DELETE FROM ";
//    private final Connection connection;
//    private final String tableName;
//    private final RowMapper<T> rowMapper;
//    private final Parser<T> parser;
//
//
//    protected AbstractDao(Connection connection, RowMapper<T> rowMapper, String tableName, Parser<T> parser) {
//        this.connection = connection;
//        this.tableName = tableName;
//        this.rowMapper = rowMapper;
//        this.parser = parser;
//    }
//
//    protected List<Entity> executeQuery(String query, Object... params) throws PersistentException {
//        List<Entity> result = new ArrayList<>();
//        try (PreparedStatement preparedStatement = createStatement(query, params);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//            while (resultSet.next()) {
//                Entity resultItem = rowMapper.map(resultSet);
//                result.add(resultItem);
//            }
//            return result;
//        } catch (SQLException e) {                // TODO add message
//            throw new PersistentException(e);
//        }
//    }
//
//    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        for (int i = 1; i <= params.length; i++) {
//            preparedStatement.setObject(i, params[i - 1]);
//        }
//        return preparedStatement;
//    }
//
//    public Optional<Entity> getById(Long id) throws PersistentException {
//        return executeForSingleResult(GET_ALL + tableName + WHERE_ID, id);
//    }
//
//    public List<Entity> getAll() throws PersistentException {
//        return executeQuery(GET_ALL + tableName);
//    }
//
//    protected Optional<Entity> executeForSingleResult(String query, Object... params) throws PersistentException {
//        List<Entity> items = executeQuery(query, params);
//        if (items.size() == 1) {
//            return Optional.of(items.get(0));
//        } else if (items.size() > 1) {
//            throw new IllegalArgumentException("More than one record found");
//        } else {
//            return Optional.empty();
//        }
//    }
//
//    public void save(Entity item) throws SQLException {
//        List<Object> listFields = parser.parse(item);
//        Long itemId = (Long) listFields.get(0);
//
//        String query;
//        if (itemId == 0) {
//            query = getSaveQuery();
//        } else {
//            Collections.rotate(listFields.subList(0, listFields.size()), -1); // put item.id at the end of list
//            query = getUpdateQuery();
//        }
//        Object[] arrayFields = listFields.toArray();
//        PreparedStatement preparedStatement = createStatement(query, arrayFields);
//        preparedStatement.executeUpdate();
//    }
//
//    protected abstract String getSaveQuery();
//
//    protected abstract String getUpdateQuery();
//
//    public void removeById(Long id) throws SQLException {
//        PreparedStatement preparedStatement = createStatement(DELETE_FROM + tableName + WHERE_ID, id);
//        preparedStatement.executeUpdate();
//
//    }
//
//}
