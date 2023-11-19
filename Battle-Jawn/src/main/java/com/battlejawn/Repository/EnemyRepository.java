package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.battlejawn.Entities.Enemy.Enemy;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EnemyRepository extends JpaRepository<Enemy, Long> {
//    void deleteByDateBefore(LocalDateTime date);
    
}
