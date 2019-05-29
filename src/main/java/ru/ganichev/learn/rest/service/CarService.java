package ru.ganichev.learn.rest.service;

import ru.ganichev.learn.rest.model.Car;

import java.util.List;

public interface CarService  extends EntityRelatedService<Car> {
    List<Car> getAll();
}
