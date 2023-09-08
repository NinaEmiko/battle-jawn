package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.battlejawn.Entities.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    
}
