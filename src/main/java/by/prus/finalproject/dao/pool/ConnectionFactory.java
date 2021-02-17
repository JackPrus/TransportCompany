package by.prus.finalproject.dao.pool;

import by.prus.finalproject.exception.ConnectionPoolException;
import by.prus.finalproject.exception.PersistentException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Class implements fqctory pattern in order to create a Connection
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/sdek?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String PROPERTY_FILE_NAME = "database.properties";

    public Connection create() {
        try {Class.forName("com.mysql.cj.jdbc.Driver");
            Properties property = new Properties();

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
            if (inputStream != null) {
                property.load(inputStream);
            } else {
                throw new PersistentException("property file '" + PROPERTY_FILE_NAME + "' not found in the classpath");
            }
            return DriverManager.getConnection(URL, property);
        }catch (Exception e){
            throw new ConnectionPoolException("Can't create Connection with database", e);
        }
    }
}
