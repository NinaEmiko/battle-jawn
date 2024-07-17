package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

//import com.battlejawn.Entities.TalentTree.DPSTree;
import com.battlejawn.Entities.TalentTree.DPSTree;
import jakarta.persistence.*;

@Entity 
@DiscriminatorValue("DPS")
public class DPS extends Hero {
    public DPS() {

    }
    public DPS(String name) {
        super(name, 90, 90,3, 3, "DPS",
    new DPSTree()
    );
    }

}
