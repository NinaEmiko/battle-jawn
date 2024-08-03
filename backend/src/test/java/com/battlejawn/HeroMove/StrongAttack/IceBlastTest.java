package com.battlejawn.HeroMove.StrongAttack;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Battle.BattleStatus;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Caster;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.TalentTree.CasterTree;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.Service.*;
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
public class IceBlastTest {
    @Mock
    BattleHistoryMessageService battleHistoryMessageService;
    @Mock
    BattleSessionService battleSessionService;
    @Mock
    BattleStatusService battleStatusService;
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
    IceBlast iceBlast;

    BackStab backStab;
    BattleStatus battleStatus;
    List<String> list;

    @Test
    void iceBlastTest(){
        battleStatus = new BattleStatus();
        battleSession = new BattleSession();
        battleSession.setBattleStatus(battleStatus);
        list = new ArrayList<>();
        Caster caster = new Caster("Caster");
        CasterTree casterTree = new CasterTree();
        casterTree.setImprovedIceBlast(true);
        caster.setTalentTree(casterTree);
        caster.setResource(0);
        enemy.setHealth(100);

        when(battleStatusService.saveBattleStatus(any())).thenReturn(battleStatus);
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

        HeroMoveDTO result = iceBlast.attack(1L);

        Assertions.assertNotNull(result);
        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }

    @Test
    void iceBlastDefeatEnemyTest(){
        battleStatus = new BattleStatus();
        battleSession = new BattleSession();
        battleSession.setBattleStatus(battleStatus);
        list = new ArrayList<>();
        Caster caster = new Caster("Caster");
        CasterTree casterTree = new CasterTree();
        casterTree.setImprovedIceBlast(true);
        caster.setTalentTree(casterTree);
        caster.setResource(3);
        enemy.setHealth(1);

        when(battleStatusService.saveBattleStatus(any())).thenReturn(battleStatus);
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

        HeroMoveDTO result = iceBlast.attack(1L);

        Assertions.assertNotNull(result);
        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }

    @Test
    void iceBlastSecondNatureTrueTest(){
        battleStatus = new BattleStatus();
        battleSession = new BattleSession();
        battleSession.setBattleStatus(battleStatus);
        list = new ArrayList<>();
        Caster caster = new Caster("Caster");
        CasterTree casterTree = new CasterTree();
        casterTree.setImprovedIceBlast(true);
        casterTree.setSecondNature(true);
        caster.setTalentTree(casterTree);
        caster.setResource(3);
        enemy.setHealth(1);

        when(battleStatusService.saveBattleStatus(any())).thenReturn(battleStatus);
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

        HeroMoveDTO result = iceBlast.attack(1L);

        Assertions.assertNotNull(result);
        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }
}
