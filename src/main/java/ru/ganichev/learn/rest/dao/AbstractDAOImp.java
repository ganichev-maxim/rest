package ru.ganichev.learn.rest.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.ganichev.learn.rest.model.AbstractBaseEntity;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class AbstractDAOImp<T extends AbstractBaseEntity> implements AbstractDAO<T> {

    protected EntityManager em;

    private Class<T> persistentClass;

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public AbstractDAOImp() {
        for (Type type = getClass().getGenericSuperclass(); type != null; ) {
            if (type instanceof ParameterizedType) {
                Object parameter = ((ParameterizedType) type).getActualTypeArguments()[0];

                if (parameter instanceof Class) {
                    persistentClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
                } else {
                    if (((TypeVariable<?>) parameter).getBounds()[0] instanceof Class) {
                        persistentClass = (Class<T>) ((TypeVariable<?>) parameter).getBounds()[0];
                    } else {
                        persistentClass = (Class<T>) ((ParameterizedType) ((TypeVariable<?>) parameter).getBounds()[0]).getRawType();
                    }
                }

                break;
            } else if (type instanceof Class) {
                type = ((Class<?>) type).getGenericSuperclass();
            }
        }
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public T save(T person) {
        em.persist(person);
        return person;
    }

    @Transactional(readOnly = true)
    @Override
    public T findById(Long id) {
        return em.find(getPersistentClass(), id);
    }
}
