package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.battlejawn.Entities.Battle;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {
    
}
