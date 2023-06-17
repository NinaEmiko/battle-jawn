package com.battlejawn.Player;



import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.battlejawn.StatusAilments.StatusAilments;

@Entity
@Table(name = "tank_table")
@PrimaryKeyJoinColumn(name = "tank_id")
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
