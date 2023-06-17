package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.battlejawn.PlayerTip;

@Repository
public interface PlayerTipRepository extends JpaRepository<PlayerTip, Long> {
}

