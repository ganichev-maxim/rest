package ru.ganichev.learn.rest.service;

import org.springframework.transaction.annotation.Transactional;
import ru.ganichev.learn.rest.dao.AbstractDAO;
import ru.ganichev.learn.rest.model.AbstractBaseEntity;

public abstract class AbstractEntityRelatedService<T extends AbstractBaseEntity, D extends AbstractDAO<T>> implements EntityRelatedService<T> {

    protected D dao;

    public abstract void setDao(D dao);

    @Override
    @Transactional
    public T create(T entity) {
        return dao.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public T findById(Long id, String graph) {
        return dao.findById(id, graph);
    }
}
