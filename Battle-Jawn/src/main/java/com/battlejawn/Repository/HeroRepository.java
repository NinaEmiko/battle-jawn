package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.battlejawn.Entities.Hero;

public interface HeroRepository extends JpaRepository<Hero, Long> {
    
}
