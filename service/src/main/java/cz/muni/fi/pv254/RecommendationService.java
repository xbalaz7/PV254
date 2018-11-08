package cz.muni.fi.pv254;

import cz.muni.fi.pv254.entity.Recommendation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecommendationService {

    /**
     * Removes recommendation from database
     * @param recommendation to remove
     */
    void remove(Recommendation recommendation);

    /**
     * Adds recommendation to databse
     * @param recommendation to add
     */
    void add(Recommendation recommendation);

    /**
     * Updates existing recommendation in databse
     * @param recommendation to update
     */
    void update(Recommendation recommendation);

    /**
     * Finds all recommendations
     * @return List of all recommendations
     */
    List<Recommendation> findAll();

    /**
     * Finds recommendation by id
     * @param id of recommendation
     * @return Recommendation with specified id or null if no recommendation is found
     */
    Recommendation findById(Long id);
}
