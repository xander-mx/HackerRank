package com.gnp.hackerrank.HackerRank.repository;

import com.gnp.hackerrank.HackerRank.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
