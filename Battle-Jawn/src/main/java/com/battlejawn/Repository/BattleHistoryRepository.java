package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.battlejawn.Entities.BattleHistory;

@Repository
public interface BattleHistoryRepository extends JpaRepository<BattleHistory, Long> {
    
}
