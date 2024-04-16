package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.battlejawn.Entities.Enemy.Enemy;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyRepository extends JpaRepository<Enemy, Long> {

}

