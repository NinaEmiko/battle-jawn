package com.battlejawn.Player;

import com.battlejawn.StatusAilments.StatusAilments;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("sub-class")
public class DPS extends Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public DPS(Long id, int health, int maxHealth, int strength, int potions,
            int maxPotions, StatusAilments statusAilments) {
        super(90, 90, 20, 2, 3, statusAilments);
        this.id = id;
    }

}
