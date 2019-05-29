package ru.ganichev.learn.rest.dao;

import ru.ganichev.learn.rest.model.Car;

import java.util.List;

public interface CarDAO extends AbstractDAO<Car> {
    List<Car> getAll();

}
