package com.battlejawn.Entities.TalentTree;

import com.battlejawn.Entities.Hero.Hero;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="talent_tree")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tree_type")
public abstract class TalentTree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "talentTree", cascade = CascadeType.ALL)
    private Hero hero;

    public TalentTree() {

    }
}
