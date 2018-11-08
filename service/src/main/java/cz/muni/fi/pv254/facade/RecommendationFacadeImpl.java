package cz.muni.fi.pv254.facade;

import cz.muni.fi.pv254.MappingService;
import cz.muni.fi.pv254.RecommendationService;
import cz.muni.fi.pv254.dto.RecommendationDTO;
import cz.muni.fi.pv254.entity.Recommendation;

import javax.inject.Inject;
import java.util.List;

public class RecommendationFacadeImpl implements RecommendationFacade {

    @Inject
    private MappingService mappingService;

    @Inject
    private RecommendationService recommendationService;

    @Override
    public void remove(RecommendationDTO recommendation) {
        recommendationService.remove(mappingService.mapTo(recommendation, Recommendation.class));
    }

    @Override
    public void add(RecommendationDTO recommendation) {
        recommendationService.add(mappingService.mapTo(recommendation, Recommendation.class));
    }

    @Override
    public void update(RecommendationDTO recommendation) {
        recommendationService.update(mappingService.mapTo(recommendation, Recommendation.class));
    }

    @Override
    public List<RecommendationDTO> findAll() {
        return mappingService.mapTo(recommendationService.findAll(), RecommendationDTO.class);
    }

    @Override
    public RecommendationDTO findById(Long id) {
        return mappingService.mapTo(recommendationService.findById(id), RecommendationDTO.class);
    }
}
