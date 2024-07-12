package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

//import com.battlejawn.Entities.TalentTree.CasterTree;
import com.battlejawn.Entities.TalentTree.CasterTree;
import jakarta.persistence.*;

@Entity 
@DiscriminatorValue("CASTER")
public class Caster extends Hero {
    public Caster() {

    }
    public Caster(String name) {
        super(name, 90, 90,3, 3, "Caster",
    new CasterTree()
    );
    }

}
