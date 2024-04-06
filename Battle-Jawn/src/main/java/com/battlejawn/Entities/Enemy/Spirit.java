package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("SPIRIT")
public class Spirit extends Enemy {

    public Spirit(int level) {
        super("Spirit", 150, 150, 0, 0, 20, LocalDateTime.now(), level);
    }

}
