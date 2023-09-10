package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.battlejawn.Entities.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
    
}
