package com.gnp.hackerrank.HackerRank.entities;

import javax.persistence.*;

@Entity
@Table(name = "challenge_detail")
public class ChallengeDetail {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_challenge")
    private Challenge challenge;

    @OneToOne
    @JoinColumn(name = "id_player")
    private Player player;

    @Column(name = "review")
    private String review;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
