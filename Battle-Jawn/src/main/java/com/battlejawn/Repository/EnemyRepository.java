package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.battlejawn.Entities.PlayerTip;

public interface EnemyRepository extends JpaRepository<PlayerTip, Long> {
    
}
