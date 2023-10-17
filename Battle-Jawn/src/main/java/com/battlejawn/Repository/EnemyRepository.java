package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.battlejawn.Entities.Enemy.Enemy;

public interface EnemyRepository extends JpaRepository<Enemy, Long> {
    
}
