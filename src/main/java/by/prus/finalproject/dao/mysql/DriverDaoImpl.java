package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.dao.DriverDao;
import by.prus.finalproject.domain.Driver;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.service.DateParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDaoImpl extends BaseDaoImpl implements DriverDao {

    public DriverDaoImpl(Connection connection) {
        this.connection = connection;
    }

    private static final Logger logger = LogManager.getLogger(DriverDaoImpl.class);


    DateParser datePars = new DateParser();

    @Override
    public List<Driver> readBusy(Boolean isbusy) throws PersistentException {

        String sql = "SELECT * FROM driver WHERE  isbusy = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement(sql);
            statement.setBoolean(1, isbusy);
            resultSet = statement.executeQuery();
            List<Driver> driverList = new ArrayList<>();
            Driver driver = null;
            while (resultSet.next()){
                driver = new Driver(
                        resultSet.getString("name"),
                        resultSet.getString("licenseNo"),
                        resultSet.getBoolean("isbusy"),
                        resultSet.getDate("medical_aprovement")
                );
                driver.setIdentity(resultSet.getInt("id"));
                driverList.add(driver);
            }
            return driverList;

        } catch (SQLException e) {
            logger.error("SQL exception trying to read drivers by name");
            throw new PersistentException(e);
        }finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    @Override
    public List<Driver> readByName(String searchName) throws PersistentException {

        String sql = "SELECT * FROM driver WHERE  name LIKE ? ORDER BY id"; // order by means sorting
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement(sql);
            statement.setString(1,"%"+searchName+"%");
            resultSet = statement.executeQuery();
            List<Driver>driverList = new ArrayList<>();
            Driver driver = null;
            while (resultSet.next()){
                driver = new Driver(
                        resultSet.getString("name"),
                        resultSet.getString("licenseNo"),
                        resultSet.getBoolean("isbusy"),
                        resultSet.getDate("medical_aprovement")
                );
                driver.setIdentity(resultSet.getInt("id"));
                driverList.add(driver);
            }
            return driverList;
        } catch (SQLException e) {
            logger.error("SQL exception trying to read drivers by name");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    @Override
    public Integer create(Driver driver) throws PersistentException {

        DateParser dateParser = new DateParser();
        String sql = "INSERT INTO driver (name, licenseNo, isbusy, medical_aprovement) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenseNo());
            statement.setBoolean(3, driver.isBusy());
            statement.setDate(4, new java.sql.Date(driver.getMedicalAprovement().getTime()));
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `driver`");
                throw new PersistentException();
            }
        } catch(SQLException e) {
            logger.error("SQL exception trying to create field in table `driver`");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    @Override
    public Driver read(Integer identity) throws PersistentException {

        String sql = "SELECT * FROM driver WHERE id = (?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Driver driver = null;
            if (resultSet.next()){  // получается пустой резалтсет решить проблему!!!
                driver = new Driver(
                        resultSet.getString("name"),
                        resultSet.getString("licenseNo"),
                        resultSet.getBoolean("isbusy"),
                        resultSet.getDate("medical_aprovement")
                );
                driver.setIdentity(identity);
            }

            return driver;

        } catch(SQLException e) {
            logger.error("SQL exception trying to read from `driver`");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }

    }

    @Override
    public void update(Driver driver) throws PersistentException {

        String sql = "UPDATE `driver` SET `name` = ?, `licenseNo` = ?, `isbusy` = ?, `medical_aprovement` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenseNo());
            statement.setBoolean(3, driver.isBusy());
            statement.setDate(4, new java.sql.Date(driver.getMedicalAprovement().getTime()));
            statement.setInt(5, driver.getIdentity());
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Error trying to update information {}"+driver);
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }

    }

    @Override
    public void delete(Integer identity) throws PersistentException {

        String sql = "DELETE FROM `driver` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Somethind going wrong doing delete position {} in 'driver' table"+identity);
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }

    }
}
