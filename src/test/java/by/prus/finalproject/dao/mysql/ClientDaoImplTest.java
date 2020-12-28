package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.ClientType;
import by.prus.finalproject.bean.Driver;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.dao.Dao;
import by.prus.finalproject.dao.pool.WrapperConnector;
import by.prus.finalproject.exception.PersistentException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;

import static org.testng.Assert.*;

public class ClientDaoImplTest {

    private Dao<Client> dao;
    private Connection connection;
    private WrapperConnector wc = new WrapperConnector();

    @BeforeClass
    public void before(){
        connection=wc.getConnection();
        dao = new ClientDaoImpl(connection);
    }

    @AfterClass
    public void afterMethod(){
        wc.closeConnection();
    }

    @Test
    public void testCreate() {

//        String name, String licenseNo, boolean isBusy, Date medicalAprovement
//        String name, String data, int type_id


        Client client1 = new Client("Иоган Фихте", "Приоритетный", ClientType.PERSON);
        Client client2 = null;
        int identy = -1;


        try {
            identy = dao.create(client1);
            client1.setIdentity(identy);
            client2 = dao.read(identy);

        } catch (PersistentException e) {
            e.printStackTrace();
        }



        assertEquals(client1.getIdentity(),client2.getIdentity());
        assertEquals(client1.getName(),client2.getName());
        assertEquals(client1.getData(),client2.getData());
        assertEquals(client1.getClientType(),client2.getClientType());

    }

    @Test
    public void testRead() {

        Client client = null;
        int identy1 = 4;
        try{
            client = dao.read(identy1);
        }catch (PersistentException e){
            e.printStackTrace();
        }
        int identy2 = client.getIdentity();
        assertEquals(identy1,identy2);

    }

    @Test
    public void testUpdate() {



        Client client4 = new Client();
        client4.setIdentity(4);
        client4.setName("Афанасий");
        client4.setClientType(ClientType.PERSON);
        client4.setData("что-то");

        Client updated = null;

        try{
            dao.update(client4);
            updated = dao.read(4);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(updated.getIdentity(), client4.getIdentity());
        assertEquals(updated.getName(), client4.getName());
        assertEquals(updated.getData(), client4.getData());
        assertEquals(updated.getClientType(), client4.getClientType());

    }

    @Test
    public void testDelete() {

        Client client = null;

        try {
            dao.delete(4);
            client=dao.read(4);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(client, null);

    }

}