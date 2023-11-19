package com.battlejawn.Entities.Battle;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "battle_history")
public class BattleHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private ArrayList<String> messages = new ArrayList<>();

    public void addNewMessage(String message) {
        messages.add(message);
    }

}
