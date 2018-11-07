package cz.muni.fi.pv254.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Šimon Baláž
 */
@Table(name= "games")
@Entity
public class Game implements Serializable {
    
    @Id
    private Long gameId;
    
    @NotNull
    @Column(nullable = false)
    private String name;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommendationId")
    public List<Recommendation> recommendations;
    
    public Game(Long gameId, String name) {
        this.gameId = gameId;
        this.name = name;
    }
    
    public Game() {}
    
    public Long getId() {
        return gameId;
    }
    
    public void setId(Long gameId) {
        this.gameId = gameId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Recommendation> getRecommendations() {
        return recommendations;
    }
    
    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Game)) return false;

        Game game = (Game) o;

        return name == game.name;
    }
    
    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);       
        return result;
    }
}
