package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Window;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BuildingCustomDaoImpl implements BuildingCustomDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Window> findAllWindows(Long id) {
        String jpql = "select w from Window w where w.room.building.id= :id";
        return em.createQuery(jpql, Window.class).setParameter("id", id).getResultList();
    }

    @Override
    public List<Heater> findAllHeaters(Long id) {
        String jpql = "select h from Heater h where h.room.building.id= :id";
        return em.createQuery(jpql, Heater.class).setParameter("id", id).getResultList();
    }
}
