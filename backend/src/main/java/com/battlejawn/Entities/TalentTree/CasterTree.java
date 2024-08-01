package com.battlejawn.Entities.TalentTree;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("CASTER_TREE")
public class CasterTree extends TalentTree {

    @Column
    private boolean improvedWand1;
    @Column
    private boolean improvedWand2;
    @Column
    private boolean improvedWand3;
    @Column
    private boolean improvedFireBlast1;
    @Column
    private boolean improvedIceBlast;
    @Column
    private boolean frostBite;
    @Column
    private boolean improvedFireBlast2;
    @Column
    private boolean botany1;
    @Column
    private boolean botany2;
    @Column
    private boolean botany3;
    @Column
    private boolean resourcefulness1;
    @Column
    private boolean resourcefulness2;
    @Column
    private boolean secondNature;
    @Column
    private boolean preparation;

    public CasterTree(){
        this.improvedWand1 = false;
        this.improvedWand2 = false;
        this.improvedWand3 = false;
        this.improvedFireBlast1 = false;
        this.improvedIceBlast = false;
        this.frostBite = false;
        this.improvedFireBlast2 = false;
        this.botany1 = false;
        this.botany2 = false;
        this.botany3 = false;
        this.resourcefulness1 = false;
        this.resourcefulness2 = false;
        this.secondNature = false;
        this.preparation = false;
    }


}
