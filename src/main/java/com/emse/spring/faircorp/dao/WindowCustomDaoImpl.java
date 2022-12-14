package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class WindowCustomDaoImpl implements WindowCustomDao{

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Window> findRoomOpenWindows(Long id) {
        String jpql = "select w from Window w where w.room.id = :id and w.window_status= :status";
        return em.createQuery(jpql, Window.class)
                .setParameter("id", id)
                .setParameter("status", WindowStatus.OPEN)
                .getResultList();

    }

    @Override
    public List<Window> deleteWindowsByRoom(Long id){
        String jpql = "delete w from Window w where w.room.id = :id";
        return em.createQuery(jpql, Window.class)
                .setParameter("id", id)
                .getResultList();
    }
}
