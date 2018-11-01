package cz.muni.fi.pv254.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
/**
 *
 * @author Šimon Baláž
 */
public class Recommendation {
    
    @Id
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    private User author;
    
    @NotNull
    @Column(nullable = false)
    private Game game;
    
    @NotNull
    @Column(nullable = false)
    private boolean votedUp;
        
    private Long votesUp;
    
    private Long weightedVoteScore;
    
    private boolean earlyAccess;
    
    public Recommendation(Long id, User author, Game game, boolean votedUp, Long votesUp,
            Long weightedVoteScore, boolean earlyAccess) {
        this.id = id;
        this.author = author;
        this.game = game;
        this.votedUp = votedUp;
        this.votesUp = votesUp;
        this.weightedVoteScore = weightedVoteScore;
        this.earlyAccess = earlyAccess;
     }
       
    public Recommendation() {}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getAuthor() {
        return author;
    }
    
    public void setAuthor(User author) {
        this.author = author;
    }
    
    public Game getGame() {
        return game;
    }
    
    public void setGame(Game game) {
        this.game = game;
    }
    
    public boolean getVotedUp() {
        return votedUp;
    }
    
    public void setVotedUp(boolean votedUp) {
        this.votedUp = votedUp;
    }
    
    public Long getVotesUp() {
        return votesUp;
    }
    
    public void setVotesUp(Long votesUp) {
        this.votesUp = votesUp;
    }
    
    public Long getWeightedVoteScore() {
        return weightedVoteScore;
    }
    
    public void setWeightedVoteScore(Long weightedVoteScore) {
        this.weightedVoteScore = weightedVoteScore;
    }
    
    public boolean getEarlyAccess() {
        return earlyAccess;
    }
    
    public void setEarlyAccess(boolean earlyAccess) {
        this.earlyAccess = earlyAccess;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Recommendation)) return false;

        Recommendation recommendation = (Recommendation) o;

        if (author != null ? !author.equals(recommendation.author) : recommendation.author != null) return false;
        if (game != null ? !game.equals(recommendation.game) : recommendation.game != null) return false;             
        return (votedUp == recommendation.votedUp);
    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (game != null ? game.hashCode() : 0);       
        return result;
    }
}
