package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.battlejawn.PlayerTips;

@Repository
public interface PlayerTipsRepository extends JpaRepository<PlayerTips, Long> {
}

