package com.battlejawn.Player;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.battlejawn.StatusAilments.StatusAilments;

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
