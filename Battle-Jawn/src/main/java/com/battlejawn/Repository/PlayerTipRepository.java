package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.battlejawn.Entities.PlayerTip;

import java.util.Optional;

@Repository
public interface PlayerTipRepository extends JpaRepository<PlayerTip, Long> {


    @Modifying
    @Query("UPDATE PlayerTip e SET e.body = :newValue WHERE e.id = :idValue")
    void updatePlayerTipBodyById(@Param("newValue") String newValue, @Param("idValue") Long id);

    Optional<PlayerTip> findByBody(String body);

}

