package ru.ganichev.learn.rest.dao;

import ru.ganichev.learn.rest.model.Person;

import java.util.List;

public interface PersonDAO extends AbstractDAO<Person> {

    Person save(Person person);

    List<Person> getAll();

    Long getCount();

    void removeAll();

}
