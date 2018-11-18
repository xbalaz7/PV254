package cz.muni.fi.pv254.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Šimon Baláž
 */
@Table(name= "games")
@Entity
public class Game {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private Long steamId;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Recommendation> recommendations = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name="games_genres", joinColumns = @JoinColumn(name = "games_id"), inverseJoinColumns = @JoinColumn(name = "genres_id"))
    private Set<Genre> genres = new HashSet<>();
    
    public Game(Long steamId, String name) {
        this.name = name;
        this.steamId = steamId;
    }
    
    public Game() {}


    public Long getSteamId() {
        return steamId;
    }

    public void setSteamId(Long steamId) {
        this.steamId = steamId;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Set<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Set<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return steamId == game.steamId &&
                Objects.equals(name, game.name) &&
                Objects.equals(recommendations, game.recommendations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, steamId, recommendations);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", steamId=" + steamId +
                ", recommendations=" + recommendations +
                '}';
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public void addRecommendation(Recommendation recommendation){
        if (recommendations.contains(recommendation))
            return;

        recommendations.add(recommendation);

        recommendation.setGame(this);
    }

    public void removeRecommendation(Recommendation recommendation){
        if (!recommendations.contains(recommendation))
            return;

        recommendations.remove(recommendation);
        recommendation.setGame(null);
    }

    public void addGenre(Genre genre){
        if (genres.contains(genre))
            return;

        genres.add(genre);
        genre.addGame(this);
    }

    public void removeGenre(Genre genre){
        if (!genres.contains(genre))
            return;

        genres.remove(genre);
        genre.removeGame(this);
    }
}
