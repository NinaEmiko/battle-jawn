package com.battlejawn.Entities.Battle;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="battle_history_message")
public class BattleHistoryMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long battleSessionId;
    @Column
    private String message;
    @Column
    private LocalDateTime createdAt;

    public BattleHistoryMessage() {

    }

    public BattleHistoryMessage(Long battleSessionId, String message, LocalDateTime createdAt) {
        this.battleSessionId = battleSessionId;
        this.message = message;
        this.createdAt = createdAt;
    }
}
