package cz.muni.fi.pv254.dto;

import cz.muni.fi.pv254.entity.Recommendation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GameDTO {
    private Long id;
    private String name;
    private Long steamId;
//    private Set<Recommendation> recommendations = new HashSet<>();

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

    public long getSteamId() {
        return steamId;
    }

    public void setSteamId(long steamId) {
        this.steamId = steamId;
    }

//    public Set<Recommendation> getRecommendations() {
//        return recommendations;
//    }

//    public void setRecommendations(Set<Recommendation> recommendations) {
//        this.recommendations = recommendations;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameDTO gameDTO = (GameDTO) o;
        return steamId == gameDTO.steamId &&
                Objects.equals(name, gameDTO.name);// &&
//                Objects.equals(recommendations, gameDTO.recommendations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, steamId);
    }

    @Override
    public String toString() {
        return "GameDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", steamId=" + steamId +
//                ", recommendations=" + recommendations +
                '}';
    }
}
