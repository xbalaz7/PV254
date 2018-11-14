package cz.muni.fi.pv254;

import cz.muni.fi.pv254.dao.GameDao;
import cz.muni.fi.pv254.entity.Game;
import cz.muni.fi.pv254.exception.PersistenceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Inject
    private GameDao gameDao;

    @Override
    public void remove(Game game) {
        try {
            gameDao.remove(game);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public void add(Game game) {
        try {
            gameDao.add(game);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public void update(Game game) {
        try {
            gameDao.update(game);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public List<Game> findAll() {
        try {
            return gameDao.findAll();
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public Game findById(Long id) {
        try {
            return gameDao.findById(id);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public Game findByName(String name) {
        try {
            return gameDao.findByName(name);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public Game findBySteamId(Long id) {
        try {
            return gameDao.findBySteamId(id);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
}
