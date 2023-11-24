package com.battlejawn.Repository;

import com.battlejawn.Entities.Battle.BattleSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BattleSessionRepository extends JpaRepository<BattleSession, Long> {

}
