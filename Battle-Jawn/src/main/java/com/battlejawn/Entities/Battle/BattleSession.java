package com.battlejawn.Entities.Battle;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "battle_session")
public class BattleSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CollectionTable
    @Column
    private ArrayList<String> battleHistory = new ArrayList<>();
    @Column
    private Long heroId;
    @Column
    private Long enemyId;

    public void addNewMessage(String message) {
        battleHistory.add(message);
    }

}
