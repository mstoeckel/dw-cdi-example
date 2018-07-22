package com.cognodyne.dw.example.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.cognodyne.dw.example.api.model.Persistent;

public class PersistentService {
    @PersistenceContext(unitName = "exampleUnit")
    protected EntityManager em;

    @Transactional
    public <T extends Persistent> Optional<T> find(Class<T> cls, Long id) {
        return Optional.of(em.find(cls, id));
    }

    public <T extends Persistent> List<T> findAll(Class<T> cls) {
        return em.createQuery("select obj from " + cls.getSimpleName() + " obj", cls).getResultList();
    }

    @Transactional
    public <T extends Persistent> T create(T entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }

    @Transactional
    public <T extends Persistent> T update(T entity) {
        em.merge(entity);
        em.flush();
        return entity;
    }

    @Transactional
    public <T extends Persistent> void remove(T entity) {
        em.remove(entity);
    }
}
