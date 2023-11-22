package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.battlejawn.Entities.Enemy.Enemy;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EnemyRepository extends JpaRepository<Enemy, Long> {
//    void deleteByDateBefore(LocalDateTime date);

    @Modifying
    @Query("UPDATE Enemy e SET e.health = :newValue WHERE e.id = :idValue")
    void updateHealthById(@Param("newValue") int newValue, @Param("idValue") Long id);

}

