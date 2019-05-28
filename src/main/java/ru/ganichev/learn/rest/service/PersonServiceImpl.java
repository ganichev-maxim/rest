package ru.ganichev.learn.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.ganichev.learn.rest.dao.PersonDAO;
import ru.ganichev.learn.rest.model.Person;


import java.util.List;

@Service
public class PersonServiceImpl extends AbstractEntityRelatedService<Person, PersonDAO> implements PersonService {

    @Override
    @Autowired
    public void setDao(final PersonDAO dao) {
        this.dao = dao;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Person> getAll() {
        return dao.getAll();
    }
}
