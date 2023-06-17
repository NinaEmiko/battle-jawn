package com.battlejawn.Player;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.battlejawn.StatusAilments.StatusAilments;


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
