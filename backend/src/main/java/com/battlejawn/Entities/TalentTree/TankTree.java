package com.battlejawn.Entities.TalentTree;

import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Hero.Tank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@DiscriminatorValue("TANK_TREE")
public class TankTree extends TalentTree {

    @Column
    private boolean improvedStrike1;
    @Column
    private boolean improvedStrike2;
    @Column
    private boolean improvedStrike3;
    @Column
    private boolean improvedImpale1;
    @Column
    private boolean improvedImpale2;
    @Column
    private boolean improvedImpale3;
    @Column
    private boolean titan;
    @Column
    private boolean improvedHealth1;
    @Column
    private boolean improvedHealth2;
    @Column
    private boolean finalStand;
    @Column
    private boolean improvedBlock1;
    @Column
    private boolean improvedBlock2;
    @Column
    private boolean hydration;
    @Column
    private boolean desperation;

    public TankTree(){
        this.improvedStrike1 = false;
        this.improvedStrike2 = false;
        this.improvedStrike3 = false;
        this.improvedImpale1 = false;
        this.improvedImpale2 = false;
        this.improvedImpale3 = false;
        this.improvedBlock1 = false;
        this.improvedBlock2 = false;
        this.improvedHealth1 = false;
        this.improvedHealth2 = false;
        this.titan = false;
        this.finalStand = false;
        this.hydration = false;
        this.desperation = false;
    }
}
