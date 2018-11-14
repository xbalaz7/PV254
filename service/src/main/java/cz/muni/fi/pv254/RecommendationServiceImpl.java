package cz.muni.fi.pv254;

import cz.muni.fi.pv254.dao.RecommendationDao;
import cz.muni.fi.pv254.entity.Recommendation;
import cz.muni.fi.pv254.exception.PersistenceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Inject
    private RecommendationDao recommendationDao;

    @Override
    public void remove(Recommendation recommendation) {
        try {
            recommendationDao.remove(recommendation);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }



    @Override
    public void add(Recommendation recommendation) {
        try {
            recommendationDao.add(recommendation);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public void update(Recommendation recommendation) {
        try {
            recommendationDao.update(recommendation);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public List<Recommendation> findAll() {
        try {
            return recommendationDao.findAll();
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public Recommendation findById(Long id) {
        try {
            return recommendationDao.findById(id);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public Recommendation findBySteamId(Long id) {
        try {
            return recommendationDao.findBySteamId(id);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
}
