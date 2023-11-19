package com.battlejawn.Entities;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "battle")
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long enemyId;
    @Column
    private Long heroId;
    @Column
    private ArrayList<String> messages = new ArrayList<>();

    public void addToMessages(String message) {
        messages.add(message);
    }
    
}
