/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv254.dao;

import cz.muni.fi.pv254.entity.Recommendation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Šimon Baláž
 */
@Transactional
@Repository
public class RecommendationDaoImpl implements RecommendationDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void remove(Recommendation recommendation) {
        em.remove(em.contains(recommendation) ? recommendation : em.merge(recommendation));
    }

    @Override
    public void add(Recommendation recommendation) {
        em.persist(recommendation);
    }

    @Override
    public void update(Recommendation recommendation) {
        em.merge(recommendation);
    }

    @Override
    public List<Recommendation> findAll() {
        return em.createQuery("SELECT rec FROM Recommendation rec", Recommendation.class)
                .getResultList();
    }

    @Override
    public Recommendation findById(Long id) {
        return em.find(Recommendation.class, id);
    }
    
    
}
