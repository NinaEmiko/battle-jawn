package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.battlejawn.Player.Tank;

@Repository
public interface TankRepository extends JpaRepository<Tank, Long> {

}
