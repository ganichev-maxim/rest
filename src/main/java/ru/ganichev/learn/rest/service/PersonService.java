package ru.ganichev.learn.rest.service;

import ru.ganichev.learn.rest.model.Person;

import java.util.List;

public interface PersonService extends EntityRelatedService<Person> {

    List<Person> getAll();
}
