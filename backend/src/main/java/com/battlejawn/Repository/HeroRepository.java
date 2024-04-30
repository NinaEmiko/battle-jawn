package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.battlejawn.Entities.Hero.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
    List<Hero> findByUserAccountId(Long userAccountId);
    @Query("SELECT b FROM Hero b ORDER BY b.winCount DESC Limit 5")
    List<Hero> findByWinCount();

    @Query("UPDATE Hero b SET b.health = b.maxHealth")
    void restAllHeroes();

    @Query("SELECT b, (b.winCount - b.lossCount - (b.runCount * 2)) AS score FROM Hero b ORDER BY score DESC")
    List<Hero> findByHighScore();
}
