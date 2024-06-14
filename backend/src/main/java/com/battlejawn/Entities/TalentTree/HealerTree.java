//package com.battlejawn.Entities.TalentTree;
//
//import com.battlejawn.Entities.Hero.Healer;
//import com.battlejawn.Entities.Hero.Hero;
//import com.battlejawn.Entities.Talent.Healer.Protection.*;
//import com.battlejawn.Entities.Talent.Healer.Spirituality.*;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.ToString;
//
//@EqualsAndHashCode(callSuper = true)
//@Entity
//@Data
//@DiscriminatorValue("HEALER_TREE")
//public class HealerTree extends TalentTree {
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
//    private boolean improvedHeal1;
//    @Column
//    private boolean improvedHeal2;
//    @Column
//    private boolean improvedHeal3;
//    @Column
//    private boolean improvedHoly1;
//    @Column
//    private boolean improvedHoly2;
//    @Column
//    private boolean improvedHoly3;
//    @Column
//    private boolean botany1;
//    @Column
//    private boolean botany2;
//    @Column
//    private boolean bubble;
//    @Column
//    private boolean spirituallyAttuned;
//    @Column
//    private boolean survivalInstincts;
//    public HealerTree() {
//        this.improvedWand1 = false;
//        this.improvedWand2 = false;
//        this.improvedWand3 = false;
//        this.improvedHeal1 = false;
//        this.improvedHeal2 = false;
//        this.improvedHeal3 = false;
//        this.improvedHoly1 = false;
//        this.improvedHoly2 = false;
//        this.improvedHoly3 = false;
//        this.botany1 = false;
//        this.botany2 = false;
//        this.bubble = false;
//        this.spirituallyAttuned = false;
//        this.survivalInstincts = false;
//    }
//
//
//}
