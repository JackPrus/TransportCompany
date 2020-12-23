package by.prus.finalproject.dao.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Properties;

public class WrapperConnector {

    private static final Logger logger = LogManager.getLogger(WrapperConnector.class);

    private Connection connection;

    public WrapperConnector() {

        String url = "jdbc:mysql://localhost/sdek?useUnicode=true&serverTimezone=UTC&useSSL=false";

        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "210877");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");

        try{
            connection = DriverManager.getConnection(url, prop);
            logger.info("connection is opened");
        } catch (SQLException e) {
            logger.error("not obtained connection" + e);
        }

    }

    public Connection getConnection(){ return connection; }

    public Statement getStatement() throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            if (statement != null) {
                return statement;
            }
        }
        throw new SQLException("connection or statement is null");
    }

    public PreparedStatement getPreparedStatement(String request) throws SQLException{
        if (connection != null) {
            PreparedStatement ps = connection.prepareStatement(request);
            if (ps != null) {
                return ps;
            }
        }
        throw new SQLException("connection or preparestatement is null");
    }

    public void closePrepareStatement (PreparedStatement ps){
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                logger.error("statement is Null" + e);
            }
        }
    }

    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("statement is Null" + e);
            }
        }
    }
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("statement is Null" + e);
            }
        }
    }
// другие необходимые делегированные методы интерфейса Connection

}
