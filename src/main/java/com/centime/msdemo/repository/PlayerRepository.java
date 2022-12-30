package com.centime.msdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centime.msdemo.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
