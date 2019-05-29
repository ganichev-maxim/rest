package ru.ganichev.learn.rest.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ganichev.learn.rest.dao.AbstractDAOImpl;
import ru.ganichev.learn.rest.dao.PersonDAO;
import ru.ganichev.learn.rest.model.Person;

import java.util.List;

@Repository
public class PersonDAOImpl extends AbstractDAOImpl<Person> implements PersonDAO {



    @Override
    @Transactional(readOnly = true)
    public List<Person> getAll() {
        return em.createNamedQuery(Person.ALL_SORTED, Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    protected List<Person> getAllWithCars() {
        return em.createNamedQuery(Person.ALL_SORTED_WITH_CARS, Person.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCount() {
        return em.createNamedQuery(Person.COUNT, Long.class).getSingleResult();
    }

    @Override
    @Transactional
    public void removeAll() {
        List<Person> people = getAllWithCars();
        people.forEach(person -> em.remove(person));
    }
}
