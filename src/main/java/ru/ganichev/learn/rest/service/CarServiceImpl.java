package ru.ganichev.learn.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ganichev.learn.rest.dao.CarDAO;
import ru.ganichev.learn.rest.model.Car;

import java.util.List;

@Service
public class CarServiceImpl extends AbstractEntityRelatedService<Car, CarDAO> implements CarService {

    @Override
    @Autowired
    public void setDao(CarDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> getAll() {
        return dao.getAll();
    }
}
