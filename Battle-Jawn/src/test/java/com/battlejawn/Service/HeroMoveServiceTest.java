package com.battlejawn.Service;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Enemy.Orc;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.HeroMove.Heal.Potion;
import com.battlejawn.HeroMove.Run;
import com.battlejawn.HeroMove.Steal;
import com.battlejawn.Repository.EnemyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HeroMoveServiceTest {
    @Mock
    BattleHistoryMessageService battleHistoryMessageService;
    @Mock
    BattleSessionService battleSessionService;
    @Mock
    EnemyRepository enemyRepository;
    @Mock
    HeroService heroService;
    @Mock
    EnemyService enemyService;
    @Mock
    HeroMoveDTO heroMoveDTO;
    @Mock
    InventoryService inventoryService;
    @Mock
    BattleSession battleSession;
    @Mock
    Enemy enemy;
    @Mock
    Hero hero;
    @Mock
    Run run;
    @Mock
    Steal steal;
    @Mock
    Potion potion;
    @Mock
    BattleHistoryMessage battleHistoryMessage;
    @Mock
    List<String> battleHistoryMessageList;
    @InjectMocks
    HeroMoveService heroMoveService;
    @BeforeEach
    void setup(){
        battleHistoryMessageList = new ArrayList<>();
        enemy = new Orc(2, 105, 2, 25);
        hero = new Tank("Name");
    }
    @Test
    void heroMoveWandTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Wand", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveStrikeTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Strike", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveStabTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Stab", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveBlastTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Blast", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveHolyTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Holy", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveImpaleTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Impale", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveBackStabTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("BackStab", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveHealTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Heal", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMovePotionTest() {
        hero.setHealth(1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        when(inventoryService.findPotionCount(any())).thenReturn(1);
        doNothing().when(inventoryService).removeFromInventory(anyLong(), anyString());
        when(potion.usePotion()).thenReturn(1);
        doNothing().when(heroService).updateHero(any());
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Potion", 1L);

        assertNotNull(heroMoveDTO);
    }

    @Test
    void heroMovePotionAlmostMaxHealthTest() {
        hero.setHealth(hero.getMaxHealth() - 1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        when(inventoryService.findPotionCount(any())).thenReturn(1);
        doNothing().when(inventoryService).removeFromInventory(anyLong(), anyString());
        when(potion.usePotion()).thenReturn(30);
        doNothing().when(heroService).updateHero(any());
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Potion", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMovePotionMaxHealthTest() {
        hero.setHealth(hero.getMaxHealth());
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        when(inventoryService.findPotionCount(any())).thenReturn(1);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Potion", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveNoPotionTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        when(inventoryService.findPotionCount(any())).thenReturn(0);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Potion", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveStealTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(1);
        when(steal.useSteal()).thenReturn(true);
        doNothing().when(inventoryService).addToFirstEmptySlot(any(),anyString());
        doNothing().when(heroService).updateHero(any());
        doNothing().when(enemyService).updatePotionCountById(1, null);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Steal", 1L);
        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveStealFailTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(1);
        when(steal.useSteal()).thenReturn(false);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Steal", 1L);
        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveStealNoPotionsTest() {
        enemy.setPotions(0);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(1);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Steal", 1L);
        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveRunTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Run", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveRunFailTest() {
        when(run.useRun()).thenReturn(false);
        when(battleHistoryMessageService.createNewMessage(anyLong(), any())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
        HeroMoveDTO heroMoveDTO = heroMoveService.processRun(enemy, 1L, hero);
        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveRunSuccessTest() {
        when(run.useRun()).thenReturn(true);
        doNothing().when(heroService).updateHero(hero);
        when(battleHistoryMessageService.createNewMessage(anyLong(), any())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
        HeroMoveDTO heroMoveDTO = heroMoveService.processRun(enemy, 1L, hero);
        assertNotNull(heroMoveDTO);
    }

    @Test
    void processHeroHeal() {
        hero.setHealth(100);
        hero.setId(2L);

        doNothing().when(heroService).updateHero(hero);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        heroMoveService.processHeroHeal(1, enemy, 1L, hero);

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());

    }

    @Test
    void processHeroMaxHealthHeal() {
        hero.setId(2L);

        doNothing().when(heroService).updateHero(hero);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        heroMoveService.processHeroHeal(1, enemy, 1L, hero);

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());

    }

    @Test
    void getDamageMessageMissedTest() {
        String result = heroMoveService.getDamageMessage("Move", 0);
        Assertions.assertEquals(result, "Move missed!");
    }
    @Test
    void getDamageMessageHitTest() {
        String result = heroMoveService.getDamageMessage("Move", 1);
        Assertions.assertEquals(result, "Move did 1 damage.");
    }

    @Test
    void processHeroMoveDefeatTest() {
        enemy.setId(1L);
        hero.setId(2L);
        doNothing().when(enemyService).updateHealthById(0, 1L);
        doNothing().when(heroService).updateHero(hero);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        heroMoveService.processHeroMove(1000, enemy, 1L, hero, "Move");

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());

    }

    @Test
    void processHeroMoveDamageTest() {
        enemy.setId(1L);
        hero.setId(2L);
        doNothing().when(enemyService).updateHealthById(95, 1L);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        heroMoveService.processHeroMove(10, enemy, 1L, hero, "Move");

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
    }
}