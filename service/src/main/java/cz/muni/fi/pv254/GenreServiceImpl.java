package cz.muni.fi.pv254;

import cz.muni.fi.pv254.dao.GenreDao;
import cz.muni.fi.pv254.entity.Genre;
import cz.muni.fi.pv254.exception.PersistenceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Inject
    GenreDao genreDao;

    @Override
    public void remove(Genre genre) {
        try {
            genreDao.remove(genre);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public Genre add(Genre genre) {
        try {
            return genreDao.add(genre);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public void update(Genre genre) {
        try {
            genreDao.update(genre);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public List<Genre> findAll() {
        try {
            return genreDao.findAll();
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public Genre findById(Long id) {
        try {
            return genreDao.findById(id);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public Genre findByName(String name) {
        try {
            return genreDao.findByName(name);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
}
