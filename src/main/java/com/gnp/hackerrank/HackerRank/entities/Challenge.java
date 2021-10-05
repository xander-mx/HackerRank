package com.gnp.hackerrank.HackerRank.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "challenges")
public class Challenge {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "winner")
    private Player winner;

    @Column(name = "score")
    private String score;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "challenge", orphanRemoval = true)
    private Set<ChallengeDetail> details;

    @Column(name = "draw")
    private Boolean draw;

    public Challenge() {}

    public Challenge(Long id, Player winner, String score) {
        this.id = id;
        this.winner = winner;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Set<ChallengeDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<ChallengeDetail> details) {
        this.details = details;
    }

    public Boolean getDraw() {
        return draw;
    }

    public void setDraw(Boolean draw) {
        this.draw = draw;
    }
}
