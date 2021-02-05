package by.prus.finalproject.service.entityservice;

import by.prus.finalproject.bean.Truck;
import by.prus.finalproject.dao.TruckDao;
import by.prus.finalproject.dao.mysql.DaoHelper;
import by.prus.finalproject.dao.mysql.DaoHelperFactory;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;

import java.util.List;

public class TruckService {

    private final DaoHelperFactory daoHelperFactory;

    public TruckService(DaoHelperFactory daoHelperFactory) { this.daoHelperFactory = daoHelperFactory; }

    public void createTruck (Truck truck) throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.create()){
            TruckDao dao = daoHelper.createTruckDao();
            dao.create(truck);
        }catch (PersistentException e){
            throw new ServiceException(e);
        }
    }

    public List<Truck> readByManagerId (int managerId) throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.create()){
            TruckDao dao = daoHelper.createTruckDao();
            return dao.readByManagerId(managerId);
        }catch (PersistentException e){
            throw new ServiceException(e);
        }
    }

    public void updateTruck (Truck truck) throws ServiceException{
        try(DaoHelper daoHelper = daoHelperFactory.create()){
            TruckDao dao = daoHelper.createTruckDao();
            dao.update(truck);
        }catch (PersistentException e){
            throw new ServiceException(e);
        }
    }

    public void deleteTruck (int identity) throws ServiceException{
        try(DaoHelper daoHelper = daoHelperFactory.create()){
            TruckDao dao = daoHelper.createTruckDao();
            dao.delete(identity);
        }catch (PersistentException e){
            throw new ServiceException(e);
        }
    }

    public Truck read (int identity) throws ServiceException{
        try(DaoHelper daoHelper = daoHelperFactory.create()){
            TruckDao dao = daoHelper.createTruckDao();
            return dao.read(identity);
        }catch (PersistentException e){
            throw new ServiceException(e);
        }
    }


    public boolean isCorrectData (String truckNo, int length, int width, int height, int weight) {

        if (truckNo ==null || truckNo.equals("") || length <=0 || width <=0 || height <=0 || weight <=0){ return false; }
        if (length>=1360 || width >=255 || height >=290 || weight >= 23600){ return false; }

        return true;

    }

}
