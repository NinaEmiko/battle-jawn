package com.battlejawn.Battle.Jawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.battlejawn.Battle.Jawn.Player.DPS;

@Repository
public interface DPSRepository extends JpaRepository<DPS, Long> {

}
