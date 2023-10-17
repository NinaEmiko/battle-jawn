package com.battlejawn.Repository;

import org.springframework.stereotype.Repository;

import com.battlejawn.Entities.Toon;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ToonRepository extends JpaRepository<Toon, Long> {
    
}
