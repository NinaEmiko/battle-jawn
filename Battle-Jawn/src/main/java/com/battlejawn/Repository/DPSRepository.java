package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.battlejawn.Player.DPS;

@Repository
public interface DPSRepository extends JpaRepository<DPS, Long> {

}
