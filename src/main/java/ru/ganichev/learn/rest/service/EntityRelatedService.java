package ru.ganichev.learn.rest.service;

import ru.ganichev.learn.rest.model.AbstractBaseEntity;

public interface EntityRelatedService<T extends AbstractBaseEntity> {

    T create(T entity);

    T findById(Long id);
}
