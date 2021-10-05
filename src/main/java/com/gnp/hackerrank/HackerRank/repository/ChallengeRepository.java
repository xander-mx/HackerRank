package com.gnp.hackerrank.HackerRank.repository;

import com.gnp.hackerrank.HackerRank.entities.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
