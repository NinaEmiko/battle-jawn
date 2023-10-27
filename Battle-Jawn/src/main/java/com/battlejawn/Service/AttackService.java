package com.battlejawn.Service;

import com.battlejawn.PlayerMove.Run;
import com.battlejawn.PlayerMove.Attack.Attack;
import com.battlejawn.PlayerMove.Heal.Heal;
import com.battlejawn.PlayerMove.StrongAttack.StrongAttack;
import org.springframework.stereotype.Service;

@Service
public class AttackService {

    private Attack attack;
    private StrongAttack strongAttack;
    private Heal heal;
    private Run run;

    public void useAttack(int button, Long toonId, Long enemyId, Long battleId) {
        switch (button) {
            case 1: attack.useAttack(toonId, enemyId, battleId);
            case 2: heal.useHeal();
            case 3: strongAttack.useAttack(toonId, enemyId, battleId);
            case 4: run.useRun(toonId);;
        }
    }
    
}
