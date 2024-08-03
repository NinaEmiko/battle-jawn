package com.battlejawn.HeroMove.StrongAttack;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Battle.BattleStatus;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.DPS;
import com.battlejawn.Entities.TalentTree.DPSTree;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.HeroMove.Attack.Stab;
import com.battlejawn.Service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
public class BackStabTest {
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
    Enemy enemy;
    @Mock
    DPS dps;
    @Mock
    BattleHistoryMessage battleHistoryMessage;
    @Mock
    HeroMoveDTO heroMoveDTO;
    @Mock
    BattleStatusService battleStatusService;
    @InjectMocks
    BackStab backStab;
    BattleStatus battleStatus;
    BattleSession battleSession;
    List<String> list;
    DPSTree dpsTree;

    @BeforeEach
    void setup(){
        battleStatus = new BattleStatus();
        battleSession = new BattleSession();
        battleSession.setBattleStatus(battleStatus);
        list = new ArrayList<>();
        dps = new DPS("DPS");
        dpsTree = new DPSTree();
        dpsTree.setImprovedBackStab1(true);
        dpsTree.setImprovedBackStab2(true);
        dps.setTalentTree(dpsTree);
    }

    @Test
    void backStabNoResourceTest(){
        dps.setResource(1);

        when(battleStatusService.saveBattleStatus(any())).thenReturn(battleStatus);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(dps);
        when(heroMoveHelper.getDamageMessage(anyString(), anyInt())).thenReturn("");
        doNothing().when(enemyService).updateHealthById(anyInt(), anyLong());
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);
        when(heroMoveHelper.criticalHit(anyInt())).thenReturn(true);

        HeroMoveDTO result = backStab.attack(1L);

        Assertions.assertNotNull(result);
        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }

    @Test
    void backStabDefeatEnemyTest(){
        dps.setResource(3);
        enemy.setHealth(20);

        when(battleStatusService.saveBattleStatus(any())).thenReturn(battleStatus);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(dps);
        when(heroMoveHelper.getDamageMessage(anyString(), anyInt())).thenReturn("");
        doNothing().when(enemyService).updateHealthById(anyInt(), anyLong());
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);
        when(heroMoveHelper.criticalHit(anyInt())).thenReturn(true);

        HeroMoveDTO result = backStab.attack(1L);

        Assertions.assertNotNull(result);
        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }

    @Test
    void backStabTest(){
        dps.setResource(3);
        enemy.setHealth(100);

        when(battleStatusService.saveBattleStatus(any())).thenReturn(battleStatus);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(dps);
        when(heroMoveHelper.getDamageMessage(anyString(), anyInt())).thenReturn("");
        doNothing().when(enemyService).updateHealthById(anyInt(), anyLong());
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);
        when(heroMoveHelper.criticalHit(anyInt())).thenReturn(true);

        HeroMoveDTO result = backStab.attack(1L);

        Assertions.assertNotNull(result);
        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }
}
