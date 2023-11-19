package com.battlejawn.Repository;

import com.battlejawn.Entities.Battle.BattleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleHistoryRepository extends JpaRepository<BattleHistory, Long> {
}
