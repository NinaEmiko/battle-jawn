//package com.battlejawn.Entities.TalentTree;
//
//import com.battlejawn.Entities.Hero.Caster;
//import com.battlejawn.Entities.Hero.Hero;
//import com.battlejawn.Entities.Talent.Caster.Arcane.*;
//import com.battlejawn.Entities.Talent.Caster.Mindfulness.*;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.ToString;
//
//@EqualsAndHashCode(callSuper = true)
//@Entity
//@Data
//@DiscriminatorValue("CASTER_TREE")
//public class CasterTree extends TalentTree {
//
//    @JsonIgnore
//    @ToString.Exclude
//    @OneToOne(mappedBy = "talentTree", cascade = CascadeType.ALL)
//    private Hero hero;
//    @Column
//    private boolean improvedWand1;
//    @Column
//    private boolean improvedWand2;
//    @Column
//    private boolean improvedWand3;
//    @Column
//    private boolean improvedFireBlast1;
//    @Column
//    private boolean improvedIceBlast;
//    @Column
//    private boolean frostBite;
//    @Column
//    private boolean improvedFireBlast2;
//    @Column
//    private boolean botany1;
//    @Column
//    private boolean botany2;
//    @Column
//    private boolean botany3;
//    @Column
//    private boolean resourcefulness1;
//    @Column
//    private boolean resourcefulness2;
//    @Column
//    private boolean secondNature;
//    @Column
//    private boolean preparation;
//
//    public CasterTree(){
//        this.improvedWand1 = false;
//        this.improvedWand2 = false;
//        this.improvedWand3 = false;
//        this.improvedFireBlast1 = false;
//        this.improvedIceBlast = false;
//        this.frostBite = false;
//        this.improvedFireBlast2 = false;
//        this.botany1 = false;
//        this.botany2 = false;
//        this.botany3 = false;
//        this.resourcefulness1 = false;
//        this.resourcefulness2 = false;
//        this.secondNature = false;
//        this.preparation = false;
//    }
//
//
//}
