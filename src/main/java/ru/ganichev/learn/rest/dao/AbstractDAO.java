package ru.ganichev.learn.rest.dao;


import ru.ganichev.learn.rest.model.AbstractBaseEntity;

public interface AbstractDAO<T extends AbstractBaseEntity> {

    T save(T person);

    T findById(Long id);

    T findById(Long id, String graph);
}
