package com.battlejawn.Entities.Battle;

import lombok.Data;
import javax.persistence.*;

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

}
