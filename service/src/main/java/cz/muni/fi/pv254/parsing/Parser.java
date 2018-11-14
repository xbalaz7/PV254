package cz.muni.fi.pv254.parsing;

import org.aspectj.weaver.tools.UnsupportedPointcutPrimitiveException;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Parser {

    private List<Integer> gameIds;

    public Parser() {
        gameIds = new ArrayList<Integer>();
    }

    /**
     * TODO return list of Recommendation entities
     * TODO for all games
     */
    public void downloadGameReviews() {
        throw new UnsupportedOperationException("Not implemented yet");

    }

    public void downloadGameName() {

        throw new UnsupportedOperationException("Not implemneted yet");

    }

    public void downloadUserName() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // TODO downlaod reviews for user

    public void downloadUserReviews() {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    public void addGameId(Integer id) {
        if (id == null) {
            throw new NullPointerException("Tried to add game with null id");
        }
        gameIds.add(id);
    }

    public void removeGameId(Integer id) {
        if (id == null) {
            throw new NullPointerException("Tried to removen game with id null");
        }
        gameIds.remove(id);
    }
    public List<Integer> getGameIds() {
        return Collections.unmodifiableList(gameIds);
    }
}
