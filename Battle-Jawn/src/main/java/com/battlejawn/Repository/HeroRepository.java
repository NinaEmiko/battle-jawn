package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.battlejawn.Entities.Hero.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

    @Modifying
    @Query("UPDATE Hero e SET e.health = :updatedHealth, e.potions = :updatedPotions WHERE e.id = :id")
    int restHeroById(@Param("id") Long id, @Param("updatedHealth") int updatedHealth, @Param("updatedPotions") int updatedPotions);

    List<Hero> findByUserAccountId(Long userAccountId);
    @Query("SELECT b FROM Hero b ORDER BY b.winCount DESC Limit 5")
    List<Hero> findByWinCount();

    @Modifying
    @Query("UPDATE Hero e SET e.health = :health WHERE e.id = :id")
    void updateHealthById(@Param("health") int health, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Hero e SET e.potions = :potions WHERE e.id = :id")
    void updatePotionCountById(@Param("potions") int potions, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Hero e SET e.runCount = :runCount WHERE e.id = :id")
    void updateRunCountByHeroId(@Param("runCount") int runCount, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Hero e SET e.winCount = :winCount WHERE e.id = :id")
    void updateWinCountById(@Param("winCount") int winCount, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Hero e SET e.lossCount = :lossCount WHERE e.id = :id")
    void updateLossCountById(@Param("lossCount") int lossCount, @Param("id") Long id);
    
}
