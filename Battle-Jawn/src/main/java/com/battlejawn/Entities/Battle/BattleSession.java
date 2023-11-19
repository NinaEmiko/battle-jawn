package com.battlejawn.Entities.Battle;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "battle_session")
public class BattleSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long enemyId;
    @Column
    private Long heroId;
    @OneToOne
    @JoinColumn(name = "battle_history_id")
    private BattleHistory battleHistory;
}
