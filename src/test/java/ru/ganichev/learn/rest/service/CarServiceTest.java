package ru.ganichev.learn.rest.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ganichev.learn.rest.model.Car;

import static ru.ganichev.learn.rest.web.car.CarTestData.*;

public class CarServiceTest extends AbstractServiceTest {
    @Autowired
    CarService carService;

    @Test
    public void testSaveOk() throws Exception {
        Car car = getCreated();
        carService.create(car);
        assertMatch(carService.getAll(), car, CAR2, CAR3);
    }

    @Test
    public void testGetAll() throws Exception {
        assertMatch(carService.getAll(), CAR2, CAR3);
    }

    @Test
    public void findById() throws Exception {
        Car car = carService.findById(CAR2_ID);
        assertMatch(car, CAR2);
    }

    @Test
    public void findByIdNotFound() throws Exception {
        Car car = carService.findById(CAR_NOT_EXIST_ID);
        Assert.assertNull(car);
    }
}
