package com.battlejawn.HeroMove.Attack;

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
import com.battlejawn.Service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WandTest {
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
    Wand wand;

    @Test
    void attackCasterTest(){
        List<String> list = new ArrayList<>();
        hero = new Caster("Caster");
        CasterTree casterTree = new CasterTree();
        casterTree.setImprovedWand1(true);
        casterTree.setImprovedWand2(true);
        casterTree.setImprovedWand3(true);
        hero.setTalentTree(casterTree);
        hero.setResource(0);
        enemy.setHealth(100);

        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(hero);
        doNothing().when(heroService).updateHero(any());
        when(heroMoveHelper.getDamageMessage(anyString(), anyInt())).thenReturn("");
        doNothing().when(enemyService).updateHealthById(anyInt(), anyLong());
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);
        when(heroMoveHelper.criticalHit(anyInt())).thenReturn(true);
        when(heroMoveHelper.getDamage(anyInt(), anyInt(), anyInt())).thenReturn(1000);

        wand.attack(1L);

        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }

    @Test
    void attackHealerTest(){
        List<String> list = new ArrayList<>();
        hero = new Healer("Healer");
        HealerTree healerTree = new HealerTree();
        healerTree.setImprovedWand1(true);
        healerTree.setImprovedWand2(true);
        healerTree.setImprovedWand3(true);
        hero.setTalentTree(healerTree);
        hero.setResource(0);
        enemy.setHealth(100);

        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        when(heroService.getHeroById(any())).thenReturn(hero);
        doNothing().when(heroService).updateHero(any());
        when(heroMoveHelper.getDamageMessage(anyString(), anyInt())).thenReturn("");
        doNothing().when(enemyService).updateHealthById(anyInt(), anyLong());
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(list);
        when(heroMoveHelper.getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean())).thenReturn(heroMoveDTO);
        when(heroMoveHelper.criticalHit(anyInt())).thenReturn(true);
        when(heroMoveHelper.getDamage(anyInt(), anyInt(), anyInt())).thenReturn(1);

        wand.attack(1L);

        verify(heroMoveHelper, times(1)).getHeroMoveReturnObject(anyInt(), anyInt(), anyInt(), anyList(), anyBoolean());
    }

}
