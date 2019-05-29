package ru.ganichev.learn.rest.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ganichev.learn.rest.dao.AbstractDAOImpl;
import ru.ganichev.learn.rest.dao.CarDAO;
import ru.ganichev.learn.rest.model.Car;

import java.util.List;


@Repository
public class CarDAOImpl extends AbstractDAOImpl<Car> implements CarDAO {


    @Override
    @Transactional(readOnly = true)
    public List<Car> getAll() {
        return em.createNamedQuery(Car.ALL_SORTED, Car.class).getResultList();
    }
}
