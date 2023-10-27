package com.battlejawn.Repository;

import org.springframework.stereotype.Repository;

import com.battlejawn.Entities.Hero.Toon;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

@Repository
public interface ToonRepository extends JpaRepository<Toon, Long> {
//    void deleteByDateBefore(LocalDateTime date);
    
}
