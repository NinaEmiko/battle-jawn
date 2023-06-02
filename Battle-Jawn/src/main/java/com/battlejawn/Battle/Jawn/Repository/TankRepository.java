package com.battlejawn.Battle.Jawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.battlejawn.Battle.Jawn.Player.Tank;

@Repository
public interface TankRepository extends JpaRepository<Tank, Long> {

}
