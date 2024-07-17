package com.battlejawn.Entities.TalentTree;

import com.battlejawn.Entities.Hero.Caster;
import com.battlejawn.Entities.Hero.DPS;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Talent.DPS.Dexterity.*;
import com.battlejawn.Entities.Talent.DPS.Stealth.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("DPS_TREE")
public class DPSTree extends TalentTree {

    @Column
    private boolean improvedStab1;
    @Column
    private boolean improvedStab2;
    @Column
    private boolean improvedStab3;
    @Column
    private boolean improvedBackStab1;
    @Column
    private boolean improvedBackStab2;
    @Column
    private boolean improvedSteal1;
    @Column
    private boolean improvedSteal2;
    @Column
    private boolean energized;
    @Column
    private boolean peekaboo;
    @Column
    private boolean elation;
    @Column
    private boolean firstStrike;
    @Column
    private boolean honorAmongThieves;
    @Column
    private boolean organizedMess;
    @Column
    private boolean stickyFingaz;

    public DPSTree(){
        this.improvedStab1 = false;
        this.improvedStab2 = false;
        this.improvedStab3 = false;
        this.improvedBackStab1 = false;
        this.improvedBackStab2 = false;
        this.improvedSteal1 = false;
        this.improvedSteal2 = false;
        this.energized = false;
        this.peekaboo = false;
        this.elation = false;
        this.firstStrike = false;
        this.honorAmongThieves = false;
        this.organizedMess = false;
        this.stickyFingaz = false;
    }
}
