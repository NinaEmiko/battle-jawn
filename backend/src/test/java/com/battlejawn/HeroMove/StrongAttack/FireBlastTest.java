package com.battlejawn.HeroMove.StrongAttack;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Caster;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.TalentTree.CasterTree;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.HeroMove.Attack.Wand;
import com.battlejawn.Service.BattleHistoryMessageService;
import com.battlejawn.Service.BattleSessionService;
import com.battlejawn.Service.EnemyService;
import com.battlejawn.Service.HeroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class FireBlastTest {
    @Mock
    BattleHistoryMessageService battleHistoryMessageService;
    @Mock
    BattleSessionService battleSessionService;
    @Mock
    HeroService heroService;
    @Mock
    EnemyService enemyService;
    @Mock
    HeroMoveHelper heroMoveHelper;
    @Mock
    BattleSession battleSession;
    @Mock
    Enemy enemy;
    @Mock
    Hero hero;
    @Mock
    BattleHistoryMessage battleHistoryMessage;
    @Mock
    HeroMoveDTO heroMoveDTO;
    @InjectMocks
    FireBlast fireBlast;

    @Test
    void fireBlastTest(){
        List<String> list = new ArrayList<>();
        Caster caster = new Caster("Caster");
        CasterTree casterTree = new CasterTree();
        casterTree.setImprovedFireBlast1(true);
        casterTree.setImprovedFireBlast2(true);
        caster.setTalentTree(casterTree);
        caster.setResource(0);
        enemy.setHealth(100);

        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(caster);
        when(heroMoveHelper.getDamageMessage(anyString(), anyInt())).thenReturn("");
        doNothing().when(enemyService).updateHealthById(anyInt(), anyLong());
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);
        when(heroMoveHelper.criticalHit(anyInt())).thenReturn(true);
        when(heroMoveHelper.getDamage(anyInt(), anyInt(), anyInt())).thenReturn(1);

        HeroMoveDTO result = fireBlast.attack(1L);

        Assertions.assertNotNull(result);
        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }

    @Test
    void fireBlastDefeatEnemyTest(){
        List<String> list = new ArrayList<>();
        Caster caster = new Caster("Caster");
        CasterTree casterTree = new CasterTree();
        casterTree.setImprovedFireBlast1(true);
        casterTree.setImprovedFireBlast2(true);
        caster.setTalentTree(casterTree);
        caster.setResource(0);
        enemy.setHealth(1);

        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(caster);
        when(heroMoveHelper.getDamageMessage(anyString(), anyInt())).thenReturn("");
        doNothing().when(enemyService).updateHealthById(anyInt(), anyLong());
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);
        when(heroMoveHelper.criticalHit(anyInt())).thenReturn(true);
        when(heroMoveHelper.getDamage(anyInt(), anyInt(), anyInt())).thenReturn(1000);

        HeroMoveDTO result = fireBlast.attack(1L);

        Assertions.assertNotNull(result);
        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }
}
