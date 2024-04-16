package com.battlejawn.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "player_tip")
public class PlayerTip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String body;

    public PlayerTip(Long id, String body) {
        this.id = id;
        this.body = body;
    }

    public PlayerTip() {

    }

}