package com.battlejawn.Service;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Battle.BattleStatus;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Inventory;
import com.battlejawn.Entities.TalentTree.*;
import com.battlejawn.HeroMove.Attack.*;
import com.battlejawn.HeroMove.Block;
import com.battlejawn.HeroMove.Heal.*;
import com.battlejawn.HeroMove.Run;
import com.battlejawn.HeroMove.Steal;
import com.battlejawn.HeroMove.StrongAttack.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class HeroMoveService {
    private final Stab stab;
    private final Strike strike;
    private final Holy holy;
    private final FireBlast fireBlast;
    private final IceBlast iceBlast;
    private final Impale impale;
    private final BackStab backStab;
    private final Wand wand;
    private final Run run;
    private final Block block;
    private final Steal steal;
    private final Potion potion;
    private final Water water;
    private final Heal heal;
    private final Logger logger = Logger.getLogger(HeroMoveService.class.getName());

    @Transactional
    public HeroMoveDTO heroMove(String move, Long battleSessionId) {
        logger.info("Inside heroMove service method. Move: " + move + ". Battle Session ID: " + battleSessionId);

        try {
            switch (move) {
                case "Wand":
                    return wand.attack(battleSessionId);
                case "Strike":
                    return strike.attack(battleSessionId);
                case "Stab":
                    return stab.attack(battleSessionId);
                case "FireBlast":
                    return fireBlast.attack(battleSessionId);
                case "IceBlast":
                    return iceBlast.attack(battleSessionId);
                case "Holy":
                    return holy.attack(battleSessionId);
                case "Impale":
                    return impale.attack(battleSessionId);
                case "BackStab":
                    return backStab.attack(battleSessionId);
                case "Heal":
                    return heal.useHeal(battleSessionId);
                case "Steal":
                    return steal.processSteal(battleSessionId);
                case "Block":
                    return block.processBlock(battleSessionId);
                case "Potion":
                    return potion.processPotion(battleSessionId);
                case "Water":
                    return water.processWater(battleSessionId);
                case "Run":
                    return run.processRun(battleSessionId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while routing to hero move: " + e.getMessage() + ".");
        }
        return null;
    }
}
