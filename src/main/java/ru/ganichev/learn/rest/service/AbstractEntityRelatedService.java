package ru.ganichev.learn.rest.service;

import org.springframework.transaction.annotation.Transactional;
import ru.ganichev.learn.rest.dao.AbstractDAO;
import ru.ganichev.learn.rest.model.AbstractBaseEntity;

public class AbstractEntityRelatedService<T extends AbstractBaseEntity, D extends AbstractDAO<T>> implements EntityRelatedService<T> {

    protected D dao;

    public void setDao(D dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public T create(T person) {
        return dao.save(person);
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(Long id) {
        return dao.findById(id);
    }
}
