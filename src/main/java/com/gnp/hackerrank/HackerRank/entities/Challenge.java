package com.gnp.hackerrank.HackerRank.entities;

import javax.persistence.*;

@Entity
@Table(name = "challenges")
public class Challenge {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "winner")
    private Player winner;

    @Column(name = "score")
    private String score;

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
}
