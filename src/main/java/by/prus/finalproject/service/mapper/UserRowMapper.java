package by.prus.finalproject.service.mapper;

import by.prus.finalproject.bean.User;
import by.prus.finalproject.exception.PersistentException;

import java.sql.ResultSet;
import java.sql.SQLException;

//public class UserRowMapper implements RowMapper<User> {
//
//    private static final String COLUMN_LABEL_ID = "id";
//    private static final String COLUMN_LABEL_LOGIN = "login";
//    private static final String COLUMN_LABEL_PASSWORD = "password";
//
//
//    @Override
//    public User map(ResultSet resultSet) throws PersistentException {
//        User user = null;
//        try{
//            Integer id = resultSet.getInt(COLUMN_LABEL_ID);
//            String login = resultSet.getString(COLUMN_LABEL_LOGIN);
//            String password = resultSet.getString(COLUMN_LABEL_PASSWORD);
//
//            user = new User(id, login, password);
//            return user;
//        }catch (SQLException e){
//            throw new PersistentException(e);
//        }
//
//    }
//
//}
