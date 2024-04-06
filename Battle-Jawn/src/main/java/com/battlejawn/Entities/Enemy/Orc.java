package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("ORC")
public class Orc extends Enemy {

    public Orc(int level) {
        super("Orc", 100, 100, 2, 3, 15, LocalDateTime.now(), level);
    }

}
