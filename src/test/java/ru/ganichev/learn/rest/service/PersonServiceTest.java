package ru.ganichev.learn.rest.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ganichev.learn.rest.model.Person;
import ru.ganichev.learn.rest.web.car.CarTestData;

import static ru.ganichev.learn.rest.web.car.CarTestData.CAR2;
import static ru.ganichev.learn.rest.web.person.PersonTestData.*;

public class PersonServiceTest extends AbstractServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    public void testSaveOk() throws Exception {
        Person person = getCreated();
        personService.create(person);
        assertMatch(personService.getAll(), person, PERSON2, PERSON3, PERSON_WITHOUT_CAR);
    }

    @Test
    public void testGetAll() throws Exception {
        assertMatch(personService.getAll(), PERSON2, PERSON3, PERSON_WITHOUT_CAR);
    }

    @Test
    public void findById() throws Exception {
        Person person = personService.findById(PERSON2_ID);
        assertMatch(person, PERSON2);
    }

    @Test
    public void findByIdNotFound() throws Exception {
        Person person = personService.findById(PERSON_NOT_EXIST_ID);
        Assert.assertNull(person);
    }

    @Test
    public void findByIdWithCars() throws Exception {
        Person person = personService.findById(PERSON2_ID, Person.WITH_CARS_GRAPH);
        assertMatch(person, PERSON2);
        CarTestData.assertMatch(person.getCars().get(0), CAR2);
    }

    @Test
    public void testCountPersons() throws Exception {
        Long count = personService.getCount();
        Assert.assertTrue(count.equals(PERSON_COUNT));
    }

}
