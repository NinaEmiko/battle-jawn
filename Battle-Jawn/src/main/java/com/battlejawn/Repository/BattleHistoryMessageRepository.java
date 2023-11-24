package com.battlejawn.Repository;

import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BattleHistoryMessageRepository extends JpaRepository<BattleHistoryMessage, Long> {

    @Query("SELECT e.message FROM BattleHistoryMessage e WHERE e.battleSessionId = :filterValue")
    List<String> findMessagesByBattleSessionId(@Param("filterValue") Long filterValue);
}
