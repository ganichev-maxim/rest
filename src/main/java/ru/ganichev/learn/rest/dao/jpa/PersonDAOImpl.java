package ru.ganichev.learn.rest.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ganichev.learn.rest.dao.AbstractDAOImp;
import ru.ganichev.learn.rest.dao.PersonDAO;
import ru.ganichev.learn.rest.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PersonDAOImpl extends AbstractDAOImp<Person> implements PersonDAO {

    @Override
    @PersistenceContext(unitName = "carPersistenceUnit")
    public void setEm(EntityManager em) {
        super.setEm(em);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Person> getAll() {
        return em.createNamedQuery(Person.ALL_SORTED, Person.class).getResultList();
    }
}
