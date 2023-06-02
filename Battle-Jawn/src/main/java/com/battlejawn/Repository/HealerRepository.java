package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.battlejawn.Player.Healer;

@Repository
public interface HealerRepository extends JpaRepository<Healer, Long> {

}
