/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv254.dao;

import cz.muni.fi.pv254.entity.Game;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Šimon Baláž
 */
@Transactional
@Repository
public class GameDaoImpl implements GameDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void remove(Game game) {
        em.remove(em.contains(game) ? game : em.merge(game));
    }

    @Override
    public void add(Game game) {
        em.persist(game);
    }

    @Override
    public void update(Game game) {
        em.merge(game);
    }

    @Override
    public List<Game> findAll() {
        return em.createQuery("SELECT g FROM Game g", Game.class)
                .getResultList();
    }

    @Override
    public Game findById(Long id) {
        return em.find(Game.class, id);
    }

    @Override
    public Game findByName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Cannot search for null name");

        try {
            return em.createQuery("SELECT g FROM Game g WHERE name =:name",
                        Game.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
   
    
}
