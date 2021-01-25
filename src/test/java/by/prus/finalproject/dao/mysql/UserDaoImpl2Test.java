package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.bean.*;
import by.prus.finalproject.dao.Dao;
import by.prus.finalproject.dao.pool.WrapperConnector;
import by.prus.finalproject.exception.PersistentException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

class UserDaoImplTest {

    private Dao<User> dao;
    private Connection connection;
    private WrapperConnector wc = new WrapperConnector();

    @BeforeClass
    public void before(){
        connection=wc.getConnection();
        dao = new UserDaoImpl(connection);
    }
    @AfterClass
    public void afterMethod(){
        wc.closeConnection();
    }

    @Test
    public void testCreate() {

        User user1 = new User();
        user1.setLogin("ivan");
        user1.setPassword("2121");
        user1.setEmail("vasya@mail.ru");
        user1.setRole(Role.CLIENT);

        User user2 = null;
        int identy = -1;

        try {
            identy = dao.create(user1);
            user1.setIdentity(identy);
            user2 = dao.read(identy);

        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(user1.getIdentity(),user2.getIdentity());
        assertEquals(user1.getLogin(), user2.getLogin());
        assertEquals(user1.getPassword(), user2.getPassword());
        assertEquals(user1.getEmail(),user2.getEmail());
        assertEquals(user1.getRole(),user2.getRole());

    }

    @Test
    public void testRead() {

        User user = null;
        int identy1 = 1;

        try{
            user = dao.read(identy1);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        int identy2 = user.getIdentity();
        assertEquals(identy1,identy2);

    }

    @Test
    public void testUpdate() {

        User user1 = new User();
        user1.setLogin("vasiliy");
        user1.setPassword("3434");
        user1.setEmail("vasya@mail.ru");
        user1.setRole(Role.CLIENT);
        user1.setIdentity(1);

        User user2 = null;

        try{
            dao.update(user1);
            user2 = dao.read(1);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(user1.getIdentity(),user2.getIdentity());
        assertEquals(user1.getLogin(), user2.getLogin());
        assertEquals(user1.getPassword(), user2.getPassword());
        assertEquals(user1.getEmail(),user2.getEmail());
        assertEquals(user1.getRole(),user2.getRole());

    }

    @Test
    public void testDelete() { }

    @Test
    public void testReadAllUsers() throws PersistentException {

        List<User> userList1 = ((UserDaoImpl) dao).readAllUsers();

        assertEquals(userList1.size(),1);
        assertEquals((int)userList1.get(0).getIdentity(), 1);
        assertEquals(userList1.get(0).getLogin(), "ivan");


    }
}