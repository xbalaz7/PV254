package cz.muni.fi.pv254.facade;

import cz.muni.fi.pv254.dto.GenreDTO;

import java.util.List;

public interface GenreFacade {
    /**
     * Removes genre from the database
     *
     * @param genre to be removed
     */
    void remove(GenreDTO genre);

    /**
     * Adds genre to the database
     *
     * @param genre to add
     */
    GenreDTO add(GenreDTO genre);

    /**
     * Updates existing genre in the database
     * @param genre user with changed properties
     */
    void update(GenreDTO genre);

    /**
     * Returns all genres from the database
     * @return List of all genres in the database
     */
    List<GenreDTO> findAll();

    /**
     * Finds particular genre specified by parameter id
     * @param id id number of genre
     * @return genre with id equal to parameter id, or null if no such genre found
     */
    GenreDTO findById(Long id);

    /**
     * Finds particular user specified by name
     * @param name name of genre
     * @return genre with email equal to parameter name, or null if no such genre found
     */
    GenreDTO findByName(String name);
}
