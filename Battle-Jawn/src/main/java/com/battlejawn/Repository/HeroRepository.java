package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.battlejawn.Entities.Hero.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

    @Modifying
    @Query("UPDATE Hero e SET e.health = :newValue WHERE e.id = :idValue")
    void updateHealthById(@Param("newValue") int newValue, @Param("idValue") Long id);

    @Modifying
    @Query("UPDATE Hero e SET e.potions = :newValue WHERE e.id = :idValue")
    void updatePotionCountById(@Param("newValue") int newValue, @Param("idValue") Long id);

    @Modifying
    @Query("UPDATE Hero e SET e.runCount = :newValue WHERE e.id = :idValue")
    void updateRunCountByHeroId(@Param("newValue") int newValue, @Param("idValue") Long id);

    @Modifying
    @Query("UPDATE Hero e SET e.winCount = :newValue WHERE e.id = :idValue")
    void updateWinCountById(@Param("newValue") int newValue, @Param("idValue") Long id);
    
}
