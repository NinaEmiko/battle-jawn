package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

//import com.battlejawn.Entities.TalentTree.TankTree;
import jakarta.persistence.*;

@Entity 
@DiscriminatorValue("TANK")
public class Tank extends Hero {

    public Tank() {

    }
    public Tank(String name) {
        super(name, 120, 120,3, 3, "Tank"
//    new TankTree()
    );
    }
    
}
