package com.battlejawn.Entities.TalentTree;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("HEALER_TREE")
public class HealerTree extends TalentTree {

    @Column
    private boolean improvedWand1;
    @Column
    private boolean improvedWand2;
    @Column
    private boolean improvedWand3;
    @Column
    private boolean improvedHeal1;
    @Column
    private boolean improvedHeal2;
    @Column
    private boolean improvedHeal3;
    @Column
    private boolean improvedHoly1;
    @Column
    private boolean improvedHoly2;
    @Column
    private boolean improvedHoly3;
    @Column
    private boolean botany1;
    @Column
    private boolean botany2;
    @Column
    private boolean bubble;
    @Column
    private boolean spirituallyAttuned;
    @Column
    private boolean survivalInstincts;
    public HealerTree() {
        this.improvedWand1 = false;
        this.improvedWand2 = false;
        this.improvedWand3 = false;
        this.improvedHeal1 = false;
        this.improvedHeal2 = false;
        this.improvedHeal3 = false;
        this.improvedHoly1 = false;
        this.improvedHoly2 = false;
        this.improvedHoly3 = false;
        this.botany1 = false;
        this.botany2 = false;
        this.bubble = false;
        this.spirituallyAttuned = false;
        this.survivalInstincts = false;
    }


}
