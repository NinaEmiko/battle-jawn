package com.battlejawn.Player;

import com.battlejawn.StatusAilments.StatusAilments;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("sub-class")
public class Healer extends Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Healer(Long id, int health, int maxHealth, int strength, int potions,
            int maxPotions, StatusAilments statusAilments) {
        super(100, 100, 17, 0, 0, statusAilments);
        this.id = id;
    }
}
