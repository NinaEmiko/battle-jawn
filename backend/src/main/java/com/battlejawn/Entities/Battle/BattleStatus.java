package com.battlejawn.Entities.Battle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "battle_status")
public class BattleStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "battleStatus", cascade = CascadeType.ALL)
    private BattleSession battleSession;
    @Column
    private boolean heroParalyzed;
    @Column
    private boolean heroBlocking;
    @Column
    private boolean enemyFrozen;
    @Column
    private boolean enemyParalyzed;
}
