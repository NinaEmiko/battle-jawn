package com.battlejawn.HeroMove.Attack;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.DPS;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Entities.TalentTree.DPSTree;
import com.battlejawn.Entities.TalentTree.TankTree;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.Service.BattleHistoryMessageService;
import com.battlejawn.Service.BattleSessionService;
import com.battlejawn.Service.EnemyService;
import com.battlejawn.Service.HeroService;
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
public class StabTest {
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
    DPS dps;
    @Mock
    BattleHistoryMessage battleHistoryMessage;
    @Mock
    HeroMoveDTO heroMoveDTO;
    @Mock
    DPSTree dpsTree;
    List<String> list;
    @InjectMocks
    Stab stab;

    @BeforeEach
    void setup() {
        list = new ArrayList<>();
        dps = new DPS("DPS");
        dpsTree = new DPSTree();
        dpsTree.setImprovedStab1(true);
        dpsTree.setImprovedStab2(true);
        dps.setTalentTree(dpsTree);
        dps.setResource(1);
        enemy.setHealth(100);
    }

    @Test
    void attackStabTest(){
        dpsTree.setImprovedStab3(true);

        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(dps);
        doNothing().when(heroService).updateHero(any());
        when(heroMoveHelper.getDamageMessage(anyString(), anyInt())).thenReturn("");
        doNothing().when(enemyService).updateHealthById(anyInt(), anyLong());
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);
        when(heroMoveHelper.criticalHit(anyInt())).thenReturn(true);
        when(heroMoveHelper.getDamage(anyInt(), anyInt(), anyInt())).thenReturn(1000);

        HeroMoveDTO result = stab.attack(1L);

        Assertions.assertNotNull(result);
        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }

    @Test
    void attackStab0DamageTest(){
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(dps);
        when(heroMoveHelper.getDamageMessage(anyString(), anyInt())).thenReturn("");
        doNothing().when(enemyService).updateHealthById(anyInt(), anyLong());
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);
        when(heroMoveHelper.criticalHit(anyInt())).thenReturn(true);
        when(heroMoveHelper.getDamage(anyInt(), anyInt(), anyInt())).thenReturn(0);

        HeroMoveDTO result = stab.attack(1L);

        Assertions.assertNotNull(result);
        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }
}
