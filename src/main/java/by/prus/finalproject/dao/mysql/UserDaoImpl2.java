package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.bean.*;
import by.prus.finalproject.dao.UserDao;
import by.prus.finalproject.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl2 extends BaseDaoImpl implements UserDao {


    private static final Logger logger = LogManager.getLogger(UserDaoImpl2.class);

    public UserDaoImpl2(Connection connection) {
        super(connection);
    }


    @Override
    public Integer create(User user) throws PersistentException {
        String sql = "INSERT INTO user (login, password, email, role) VALUE (?,?,?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3,user.getEmail());
            statement.setString(4, user.getRole().getRoleName());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `user`");
                throw new PersistentException();
            }
        } catch(SQLException e) {
            logger.error("SQL exception trying to create field in table `user`");
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch(SQLException e) {}
            try {
                if (statement!=null){
                    statement.close();
                }
            } catch(SQLException e) {}
        }
    }

    @Override
    public User read(Integer identity) throws PersistentException {
        String sql = "SELECT * FROM user WHERE id = (?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            User user = null;
            //truck_no, `length_capasity(cm)`, `width_capasity(cm)`, `height_capasity(cm)`, isbusy, manager_id
            if (resultSet.next()){
                user = new User();

                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.getRole(resultSet.getString("role")));
                user.setIdentity(identity);
            }
            return user;

        } catch(SQLException e) {
            logger.error("SQL exception trying to read from `user`");
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch(SQLException e) {}
            try {
                if (statement!=null){
                    statement.close();
                }
            } catch(SQLException e) {}
        }
    }

    @Override
    public void update(User user) throws PersistentException {

        String sql = "UPDATE `user` SET `login` = ?, `password` = ?, 'email' =?, 'role'=?  WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,user.getLogin());
            statement.setString(2,user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4,user.getRole().getRoleName());
            statement.setInt(5, user.getIdentity());
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Error trying to update information of in user table ");
            throw new PersistentException(e);
        } finally {
            try {
                if (statement!=null){
                    statement.close();
                }
            } catch(SQLException e) {}
        }

    }

    @Override
    public void delete(Integer identity) throws PersistentException {

        String sql = "DELETE FROM `user` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Somethind going wrong doing delete position {} in 'user' table"+identity);
            throw new PersistentException(e);
        } finally {
            try {
                if (statement!=null){
                    statement.close();
                }
            } catch(SQLException e) {}
        }

    }

    @Override
    public List<User> readAllUsers() throws PersistentException {
        String sql = "SELECT * FROM user";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<User> userList = new ArrayList<>();
            if (resultSet.next()){
                Integer identity = resultSet.getInt("id");
                User user = read(identity);  // read all data for User from method above method
                userList.add(user);
            }
            return userList;

        } catch(SQLException e) {
            logger.error("SQL exception trying to readList of users");
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch(SQLException e) {}
            try {
                if (statement!=null){
                    statement.close();
                }
            } catch(SQLException e) {}
        }
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws PersistentException {
        String sql = "SELECT * FROM user WHERE login = ? and password = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()){
                user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.getRole(resultSet.getString("role")));
                user.setIdentity(resultSet.getInt("id"));
            }
            return user;

        } catch(SQLException e) {
            logger.error("SQL exception trying to read from `user` by login and password");
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch(SQLException e) {}
            try {
                if (statement!=null){
                    statement.close();
                }
            } catch(SQLException e) {}
        }
    }
}
