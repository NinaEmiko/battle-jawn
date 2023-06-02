package com.battlejawn.Battle.Jawn.Player;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

import com.battlejawn.Battle.Jawn.StatusAilments.StatusAilments;

// @Entity
// @Table(name = "tanks")
public class Tank extends Player {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id")
    // private Long id;
    // @Column(name = "health")
    // private int health;
    // @Column(name = "max_health")
    // private int maxHealth;
    // @Column(name = "strength")
    // private int strength;
    // @Column(name = "potions")
    // private int potions;
    // @Column(name = "maxPotions")
    // private int maxPotions;
    // @Enumerated(EnumType.STRING)
    // @Column(name = "status_ailments")
    // private StatusAilments statusAilments;

    public Tank(Long id, int health, int maxHealth, int strength, int potions,
            int maxPotions, StatusAilments statusAilments) {
        super(120, 120, 15, 3, 3, statusAilments);
        // this.id = id;
    }

}
