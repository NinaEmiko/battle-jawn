package com.battlejawn.Repository;

import com.battlejawn.Entities.Battle.BattleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattleStatusRepository extends JpaRepository<BattleStatus, Long> {
    BattleStatus findBattleStatusByBattleSessionId(Long battleSessionId);
}
