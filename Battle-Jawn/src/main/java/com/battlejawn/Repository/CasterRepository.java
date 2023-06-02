package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.battlejawn.Player.Caster;

@Repository
public interface CasterRepository extends JpaRepository<Caster, Long> {

}
