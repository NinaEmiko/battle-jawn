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
@Table(name = "additional_inventory")
public class AdditionalInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long heroId;
    @Column
    private String slotOne;
    @Column
    private String slotTwo;
    @Column
    private String slotThree;
    @Column
    private String slotFour;

    public AdditionalInventory(Long heroId) {
        this.slotOne = "Potion";
        this.slotTwo = "Potion";
        this.slotThree = "Potion";
        this.slotFour = "";
        this.heroId = heroId;
    }
}
