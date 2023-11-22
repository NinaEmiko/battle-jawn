package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.battlejawn.Entities.Hero.Hero;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
//    void deleteByDateBefore(LocalDateTime date);

    @Modifying
    @Query("UPDATE Hero e SET e.health = :newValue WHERE e.id = :idValue")
    void updateHealthById(@Param("newValue") int newValue, @Param("idValue") Long id);
    
}
