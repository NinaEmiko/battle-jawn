package com.battlejawn.Entities;

import com.battlejawn.Entities.Hero.Hero;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "inventory", cascade = CascadeType.ALL)
    private Hero hero;
    @Column
    private String slotOne;
    @Column
    private String slotTwo;
    @Column
    private String slotThree;
    @Column
    private String slotFour;
    @Column
    private String slotFive;
    @Column
    private String slotSix;
    @Column
    private String slotSeven;
    @Column
    private String slotEight;
    @Column
    private String slotNine;
    @Column
    private String slotTen;
    @Column
    private String slotEleven;
    @Column
    private String slotTwelve;

    public Inventory() {
        this.slotOne = "";
        this.slotTwo = "";
        this.slotThree = "";
        this.slotFour = "";
        this.slotFive = "";
        this.slotSix = "";
        this.slotSeven = "";
        this.slotEight = "";
        this.slotNine = "";
        this.slotTen = "";
        this.slotEleven = "";
        this.slotTwelve = "";
    }
}
