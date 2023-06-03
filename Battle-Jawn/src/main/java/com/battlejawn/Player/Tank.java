package com.battlejawn.Player;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.battlejawn.StatusAilments.StatusAilments;

@Entity
@DiscriminatorValue("sub-class")
public class Tank extends Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Tank(Long id, int health, int maxHealth, int strength, int potions,
            int maxPotions, StatusAilments statusAilments) {
        super(120, 120, 15, 3, 3, statusAilments);
        this.id = id;
    }
}
