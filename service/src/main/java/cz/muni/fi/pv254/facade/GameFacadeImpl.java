package cz.muni.fi.pv254.facade;

import cz.muni.fi.pv254.GameService;
import cz.muni.fi.pv254.MappingService;
import cz.muni.fi.pv254.dto.GameDTO;
import cz.muni.fi.pv254.entity.Game;

import javax.inject.Inject;
import java.util.List;

public class GameFacadeImpl implements GameFacade {

    @Inject
    private MappingService mappingService;

    @Inject
    private GameService gameService;

    @Override
    public void remove(GameDTO game) {
        gameService.remove(mappingService.mapTo(game, Game.class));
    }

    @Override
    public void add(GameDTO game) {
        gameService.add(mappingService.mapTo(game, Game.class));
    }

    @Override
    public void update(GameDTO game) {
        gameService.update(mappingService.mapTo(game, Game.class));
    }

    @Override
    public List<GameDTO> findAll() {
        return mappingService.mapTo(gameService.findAll(), GameDTO.class);
    }

    @Override
    public GameDTO findById(Long id) {
        return mappingService.mapTo(gameService.findById(id), GameDTO.class);
    }

    @Override
    public GameDTO findByName(String name) {
        return mappingService.mapTo(gameService.findByName(name), GameDTO.class);
    }
}
