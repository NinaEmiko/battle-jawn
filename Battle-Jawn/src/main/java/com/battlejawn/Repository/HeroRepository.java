package com.battlejawn.Repository;

import org.springframework.stereotype.Repository;

import com.battlejawn.Entities.Hero.Hero;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
//    void deleteByDateBefore(LocalDateTime date);
    
}
