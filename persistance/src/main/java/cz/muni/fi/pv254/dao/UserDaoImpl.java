package cz.muni.fi.pv254.dao;

import cz.muni.fi.pv254.entity.Recommendation;
import cz.muni.fi.pv254.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void remove(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @Override
    public User add(User user) {
        return em.merge(user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException("Cannot search for null e-mail");

        try {
            return em.createQuery("SELECT u FROM User u where email =:email",
                        User.class).setParameter("email", email).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public User findBySteamId(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Cannot search for steam id null");
        }
        try {
            return em.createQuery("Select user From User user Where steamId = :id",
                    User.class).setParameter("id", id).getSingleResult();

        }
        catch (NoResultException e) {
            return null;
        }
    }
}
