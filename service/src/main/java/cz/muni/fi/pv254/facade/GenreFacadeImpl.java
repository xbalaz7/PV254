package cz.muni.fi.pv254.facade;

import cz.muni.fi.pv254.GameService;
import cz.muni.fi.pv254.GenreService;
import cz.muni.fi.pv254.MappingService;
import cz.muni.fi.pv254.dto.GenreDTO;
import cz.muni.fi.pv254.entity.Genre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class GenreFacadeImpl implements GenreFacade {

    @Inject
    private MappingService mappingService;

    @Inject
    private GenreService genreService;

    @Override
    public void remove(GenreDTO genre) {
        genreService.remove(mappingService.mapTo(genre, Genre.class));
    }

    @Override
    public GenreDTO add(GenreDTO genre) {
        Genre g = genreService.findByName(genre.getName());
        if (g == null)
            g = genreService.add(mappingService.mapTo(genre, Genre.class));
        return mappingService.mapTo(g, GenreDTO.class);
    }

    @Override
    public void update(GenreDTO genre) {
        genreService.update(mappingService.mapTo(genre, Genre.class));
    }

    @Override
    public List<GenreDTO> findAll() {
        return mappingService.mapTo(genreService.findAll(), GenreDTO.class);
    }

    @Override
    public GenreDTO findById(Long id) {
        return mappingService.mapTo(genreService.findById(id), GenreDTO.class);
    }

    @Override
    public GenreDTO findByName(String name) {
        return mappingService.mapTo(genreService.findByName(name), GenreDTO.class);
    }
}
