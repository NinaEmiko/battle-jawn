package com.battlejawn.HeroMove.StrongAttack;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Caster;
import com.battlejawn.Entities.Hero.Healer;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.TalentTree.CasterTree;
import com.battlejawn.Entities.TalentTree.HealerTree;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.HeroMove.Attack.Wand;
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
public class HolyTest {
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
    Holy holy;
    List<String> list;
    HealerTree healerTree;

    @BeforeEach
    void setup(){
        list = new ArrayList<>();
        hero = new Healer("Healer");
        healerTree = new HealerTree();
        healerTree.setImprovedHoly1(true);
        healerTree.setImprovedHoly2(true);
        hero.setTalentTree(healerTree);
        enemy.setHealth(100);
    }

    @Test
    void holyDefeatEnemyTest(){
        hero.setResource(3);
        healerTree.setImprovedHoly3(true);

        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(hero);
        when(heroMoveHelper.criticalHit(anyInt())).thenReturn(true);
        when(heroMoveHelper.getDamage(anyInt(), anyInt(), anyInt())).thenReturn(1);

        when(heroMoveHelper.getDamageMessage(anyString(), anyInt())).thenReturn("");
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage).thenReturn(battleHistoryMessage);
        doNothing().when(enemyService).updateHealthById(anyInt(), anyLong());
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);

        HeroMoveDTO result = holy.attack(1L);

        Assertions.assertNotNull(result);
        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }
    @Test
    void holyOneSpiritTest(){
        hero.setResource(3);
        healerTree.setImprovedHoly3(true);

        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(hero);
        when(heroMoveHelper.criticalHit(anyInt())).thenReturn(true);
        when(heroMoveHelper.getDamage(anyInt(), anyInt(), anyInt())).thenReturn(1);

        when(heroMoveHelper.getDamageMessage(anyString(), anyInt())).thenReturn("");
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage).thenReturn(battleHistoryMessage);
        doNothing().when(enemyService).updateHealthById(anyInt(), anyLong());
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);

        HeroMoveDTO result = holy.attack(1L);

        Assertions.assertNotNull(result);
        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }

    @Test
    void holyTwoSpiritImprovedHoly3FalseTest(){
        hero.setResource(2);

        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(hero);
        when(heroMoveHelper.criticalHit(anyInt())).thenReturn(true);
        when(heroMoveHelper.getDamage(anyInt(), anyInt(), anyInt())).thenReturn(1000);

        when(heroMoveHelper.getDamageMessage(anyString(), anyInt())).thenReturn("");
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage).thenReturn(battleHistoryMessage);
        doNothing().when(enemyService).updateHealthById(anyInt(), anyLong());
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);

        HeroMoveDTO result = holy.attack(1L);

        Assertions.assertNotNull(result);
        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }

    @Test
    void holyNoSpiritTest(){
        hero.setResource(0);
        healerTree.setImprovedHoly3(true);

        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(hero);
        when(heroMoveHelper.criticalHit(anyInt())).thenReturn(true);
        when(heroMoveHelper.getDamage(anyInt(), anyInt(), anyInt())).thenReturn(1);

        when(heroMoveHelper.getDamageMessage(anyString(), anyInt())).thenReturn("");
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage).thenReturn(battleHistoryMessage);
        doNothing().when(enemyService).updateHealthById(anyInt(), anyLong());
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);

        HeroMoveDTO result = holy.attack(1L);

        Assertions.assertNotNull(result);
        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }

}
