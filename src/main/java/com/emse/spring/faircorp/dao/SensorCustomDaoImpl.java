package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Sensor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SensorCustomDaoImpl implements SensorCustomDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Sensor> findBySiteText(String searchText) {
        return em.createQuery("select c from Sensor c inner join c.site s where lover(s.name) like :searchText", Sensor.class)
                .setParameter("searchText", "%" + searchText.toLowerCase() + "%")
                .getResultList();

    }
}
