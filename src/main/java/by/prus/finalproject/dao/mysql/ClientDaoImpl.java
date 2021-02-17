package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.bean.*;
import by.prus.finalproject.dao.ClientDao;
import by.prus.finalproject.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implements requests to database and response from it.
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class ClientDaoImpl extends BaseDaoImpl implements ClientDao {

    private static final Logger logger = LogManager.getLogger(ClientDaoImpl.class);

    private static final String CREATE_REQUEST = "INSERT INTO client (name, `data`, `type_id`) VALUES (?,?,?)";
    private static final String READ_REQUEST = "SELECT * FROM client WHERE id = (?)";
    private static final String FILL_ORDERLIST = "SELECT * FROM sdek.order WHERE client_id =(?)";
    private static final String UPDATE_CLIENT = "UPDATE `client` SET `name` = ?, `data` = ?, type_id =? WHERE `id` = ?";
    private static final String DELETE_CLIENT = "DELETE FROM `client` WHERE `id` = ?";
    private static final String READ_ALL_CLIENTS = "SELECT * FROM client ORDER BY name, type_id";


    public ClientDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Integer create(Client client) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(CREATE_REQUEST);
            statement.setString(1,client.getName());
            if (client.getData()!=null){
                statement.setString(2,client.getData());
            }
            statement.setInt(3,client.getClientType().getIdentity());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `client`");
                throw new PersistentException();
            }
        } catch(SQLException e) {
            logger.error("SQL exception trying to create field in table `client`");
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
    public Client read(Integer identity) throws PersistentException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_REQUEST);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Client client = null;
            //name, `data`, `type_id`
            if (resultSet.next()){
                client = new Client();

                List<Order> orderList = new ArrayList<>();
                fillOrderListWithID(FILL_ORDERLIST, identity, orderList);

                client.setIdentity(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setData(resultSet.getString("data"));
                client.setClientType(ClientType.getByIdentity(resultSet.getInt("type_id")));
                client.setOrderList(orderList);

            }
            return client;

        } catch(SQLException e) {
            logger.error("SQL exception trying to read from `client`");
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
    public void update(Client client) throws PersistentException {

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT);){

            statement.setString(1, client.getName());
            if (client.getData()!=null){
                statement.setString(2, client.getData());
            }
            statement.setInt(3,client.getClientType().getIdentity());
            statement.setInt(4,client.getIdentity());
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Error trying to update information of in client table ");
            throw new PersistentException(e);
        }
    }

    @Override
    public void delete(Integer identity) throws PersistentException {

        try(PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT)) {
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Somethind going wrong doing delete position {} in 'client' table"+identity);
            throw new PersistentException(e);
        }
    }

    public List<Client> readAll () throws PersistentException{

        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_ALL_CLIENTS)) {
            resultSet = statement.executeQuery();
            List<Client> clientList = new ArrayList<>();
            Client client = null;
            while (resultSet.next()){
                client = new Client();
                client.setIdentity(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setData(resultSet.getString("data"));
                client.setClientType(ClientType.getByIdentity(resultSet.getInt("type_id")));
                clientList.add(client);
            }
            return clientList;

        } catch(SQLException e) {
            logger.error("SQL exception trying to read List of Clients from `client`");
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch(SQLException e) {}
        }
    }

}
