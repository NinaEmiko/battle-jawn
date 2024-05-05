package com.battlejawn.HeroMove;

import com.battlejawn.Entities.Battle.BattleStatus;
import org.springframework.stereotype.Component;

@Component
public class Block {
    public boolean useBlock(BattleStatus battleStatus) {
        return battleStatus.isHeroBlocking();
    }
}
