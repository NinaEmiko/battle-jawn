package com.battlejawn.HeroMove;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Battle.BattleStatus;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Enemy.Wolf;
import com.battlejawn.Entities.Hero.DPS;
import com.battlejawn.Entities.TalentTree.DPSTree;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.HeroMove.StrongAttack.BackStab;
import com.battlejawn.Service.*;
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

@ExtendWith(MockitoExtension.class)
public class StealTest {
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
    InventoryService inventoryService;
    @InjectMocks
    Steal steal;
    BattleSession battleSession;
    List<String> list;

    @Test
    void stealFailTest(){
        list = new ArrayList<>();
        battleSession = new BattleSession();
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(dps);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);
        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(1);
        steal.processSteal(1L);

        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());

    }
    @Test
    void stealFail2Test(){
        list = new ArrayList<>();
        battleSession = new BattleSession();
        enemy = new Wolf();
        enemy.setPotions(3);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(dps);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);
        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(3);
        when(heroMoveHelper.useSteal()).thenReturn(false);
        steal.processSteal(1L);

        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());

    }

    @Test
    void stealSuccessTest(){
        list = new ArrayList<>();
        battleSession = new BattleSession();
        enemy = new Wolf();
        enemy.setPotions(1);
        dps = new DPS("DPS");
        DPSTree dpsTree = (DPSTree) dps.getTalentTree();
        dpsTree.setElation(true);
        dps.setTalentTree(dpsTree);
        enemy.setId(1L);

        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(dps);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);
        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(3);
        when(heroMoveHelper.useSteal()).thenReturn(true);
        doNothing().when(inventoryService).addToFirstEmptySlot(any(), anyString());
        doNothing().when(heroService).updateHero(any());
        doNothing().when(enemyService).updatePotionCountById(anyInt(), anyLong());

        steal.processSteal(1L);

        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());

    }
}
