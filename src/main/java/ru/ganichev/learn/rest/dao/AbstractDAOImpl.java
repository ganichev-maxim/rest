package ru.ganichev.learn.rest.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.ganichev.learn.rest.model.AbstractBaseEntity;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDAOImpl<T extends AbstractBaseEntity> implements AbstractDAO<T> {

    protected EntityManager em;

    private Class<T> persistentClass;

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public AbstractDAOImpl() {
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

    @PersistenceContext(unitName = "carPersistenceUnit")
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

    @Override
    @Transactional(readOnly = true)
    public T findById(Long id, String graph) {
        EntityGraph entityGraph = this.em.getEntityGraph(graph);
        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", entityGraph);
        return (T) this.em.find(getPersistentClass(), id, hints);
    }
}
