package com.battlejawn.Entities.Battle;

import lombok.Data;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "battle_session")
public class BattleSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long battleHistoryMessageId;
    @Column
    private Long heroId;
    @Column
    private Long enemyId;
    @Column
    private LocalDateTime createdAt;
    @JoinColumn(name = "battle_status_id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private BattleStatus battleStatus;

    public BattleSession (){
        this.battleStatus = new BattleStatus();
        this.createdAt = LocalDateTime.now();
    }
}
