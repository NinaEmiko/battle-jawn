package com.battlejawn.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.battlejawn.Entities.Roles.Toon;

@Repository
public interface ToonRepository extends JpaRepository<Toon, Long> {
    
}
