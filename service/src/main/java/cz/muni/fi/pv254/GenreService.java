package cz.muni.fi.pv254;

import cz.muni.fi.pv254.entity.Genre;

import java.util.List;

public interface GenreService {
    /**
     * Removes genre from the database
     *
     * @param genre to be removed
     */
    void remove(Genre genre);

    /**
     * Adds genre to the database
     *
     * @param genre to add
     */
    Genre add(Genre genre);

    /**
     * Updates existing genre in the database
     * @param genre user with changed properties
     */
    void update(Genre genre);

    /**
     * Returns all genres from the database
     * @return List of all genres in the database
     */
    List<Genre> findAll();

    /**
     * Finds particular genre specified by parameter id
     * @param id id number of genre
     * @return genre with id equal to parameter id, or null if no such genre found
     */
    Genre findById(Long id);

    /**
     * Finds particular user specified by name
     * @param name name of genre
     * @return genre with email equal to parameter name, or null if no such genre found
     */
    Genre findByName(String name);
}
