package com.battlejawn.Repository;

import com.battlejawn.Entities.Battle.BattleSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleSessionRepository extends JpaRepository<BattleSession, Long> {

}
