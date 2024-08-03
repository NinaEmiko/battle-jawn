package com.battlejawn.Repository;

import com.battlejawn.Entities.AdditionalInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalInventoryRepository extends JpaRepository<AdditionalInventory, Long> {
    @Query("SELECT e FROM AdditionalInventory e WHERE e.heroId = :heroId")
    AdditionalInventory findAdditionalInventoryByHeroId(@Param("heroId") Long heroId);
}
