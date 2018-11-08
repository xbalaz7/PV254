package cz.muni.fi.pv254.dto;

import cz.muni.fi.pv254.entity.Game;
import cz.muni.fi.pv254.entity.User;

import java.util.Objects;

public class RecommendationDTO {
    private Long id;
    private int steamId;
    private User author;
    private Game game;
    private boolean votedUp;
    private Long votesUp;
    private double weightedVoteScore;
    private boolean earlyAccess;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSteamId() {
        return steamId;
    }

    public void setSteamId(int steamId) {
        this.steamId = steamId;
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

    public boolean isVotedUp() {
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

    public double getWeightedVoteScore() {
        return weightedVoteScore;
    }

    public void setWeightedVoteScore(double weightedVoteScore) {
        this.weightedVoteScore = weightedVoteScore;
    }

    public boolean isEarlyAccess() {
        return earlyAccess;
    }

    public void setEarlyAccess(boolean earlyAccess) {
        this.earlyAccess = earlyAccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendationDTO that = (RecommendationDTO) o;
        return steamId == that.steamId &&
                votedUp == that.votedUp &&
                Double.compare(that.weightedVoteScore, weightedVoteScore) == 0 &&
                earlyAccess == that.earlyAccess &&
                Objects.equals(author, that.author) &&
                Objects.equals(game, that.game) &&
                Objects.equals(votesUp, that.votesUp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(steamId, author, game, votedUp, votesUp, weightedVoteScore, earlyAccess);
    }

    @Override
    public String toString() {
        return "RecommendationDTO{" +
                "id=" + id +
                ", steamId=" + steamId +
                ", author=" + author +
                ", game=" + game +
                ", votedUp=" + votedUp +
                ", votesUp=" + votesUp +
                ", weightedVoteScore=" + weightedVoteScore +
                ", earlyAccess=" + earlyAccess +
                '}';
    }
}
