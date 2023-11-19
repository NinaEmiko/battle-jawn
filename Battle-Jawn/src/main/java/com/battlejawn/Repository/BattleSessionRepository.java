package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.battlejawn.Entities.Battle.BattleSession;

@Repository
public interface BattleSessionRepository extends JpaRepository<BattleSession, Long> {
    
}
