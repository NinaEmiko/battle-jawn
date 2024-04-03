package com.battlejawn.Service;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.EnemyMove.Steal;
import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.*;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.HeroMove.Heal.Potion;
import com.battlejawn.Randomizer.Randomizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnemyMoveServiceTest {
    @Mock
    HeroService heroService;
    @Mock
    Hero hero;
    @Mock
    BattleHistoryMessageService battleHistoryMessageService;
    @Mock
    EnemyService enemyService;
    @Mock
    Enemy enemy;
    @Mock
    Randomizer randomizer;
    @Mock
    BattleSession battleSession;
    @Mock
    Steal steal;
    @Mock
    BattleSessionService battleSessionService;
    @Mock
    BattleHistoryMessage battleHistoryMessage;
    @Mock
    List<String> battleHistoryMessageList;
    @Mock
    Potion potion;
    @InjectMocks
    EnemyMoveService enemyMoveService;
    @BeforeEach
    void setup() {
        battleSession = new BattleSession();
        battleSession.setId(3L);
        battleSession.setEnemyId(1L);
        battleSession.setHeroId(2L);
        hero = new Tank("Name");
    }
    @Test
    void enemyMoveWolfBiteTest() {
        enemy = new Wolf(2);

        when(randomizer.getRandomInt(9)).thenReturn(4);

        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }
    @Test
    void enemyMoveWolfMaimTest() {
        enemy = new Wolf(2);

        when(randomizer.getRandomInt(9)).thenReturn(1);

        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }
    @Test
    void enemyMoveSpiritShadowBlastTest() {

        enemy = new Spirit(2);
        when(randomizer.getRandomInt(9)).thenReturn(4);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }
    @Test
    void enemyMoveSpiritSoulEaterTest() {

        enemy = new Spirit(2);
        when(randomizer.getRandomInt(9)).thenReturn(0);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }
    @Test
    void enemyMoveOrcPotionTest() {

        enemy = new Orc(2);
        enemy.setHealth(1);
        when(randomizer.getRandomInt(9)).thenReturn(1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }
    @Test
    void enemyMoveOrcSecondPotionTest() {

        enemy = new Orc(2);
        enemy.setHealth(41);
        when(randomizer.getRandomInt(9)).thenReturn(9);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }
    @Test
    void enemyMoveOrcStrikeTest() {

        enemy = new Orc(2);
        enemy.setHealth(41);
        when(randomizer.getRandomInt(9)).thenReturn(7);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }
    @Test
    void enemyMoveOrcImpaleTest() {

        enemy = new Orc(2);
        enemy.setHealth(41);
        when(randomizer.getRandomInt(9)).thenReturn(1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }
    @Test
    void enemyMoveThiefPotionTest() {

        enemy = new Thief(2);
        enemy.setHealth(1);
        when(randomizer.getRandomInt(9)).thenReturn(1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }
    @Test
    void enemyMoveThiefSecondPotionTest() {

        enemy = new Thief(2);
        enemy.setHealth(31);
        when(randomizer.getRandomInt(9)).thenReturn(10);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }
    @Test
    void enemyMoveThiefStabTest() {

        enemy = new Thief(2);
        enemy.setHealth(31);
        when(randomizer.getRandomInt(9)).thenReturn(2);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }
    @Test
    void enemyMoveThiefStealTest() {

        enemy = new Thief(2);
        enemy.setHealth(31);
        when(randomizer.getRandomInt(9)).thenReturn(1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }
    @Test
    void enemyMoveNullEnemyNameTest() {

        enemy = new Thief(2);
        enemy.setName("Name");
        when(randomizer.getRandomInt(9)).thenReturn(12);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNull(heroMoveDTO);
    }
    @Test
    void processPotionTest() {
        enemy = new Thief(2);
        enemy.setId(4L);
        enemy.setHealth(30);

        when(potion.usePotion()).thenReturn(30);

        doNothing().when(enemyService).updatePotionCountById(1, 4L);
        doNothing().when(enemyService).updateHealthById(60, 4L);
        when(battleHistoryMessageService.createNewMessage(anyLong(),anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        enemyMoveService.processPotion(enemy, 1L, hero);

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
    }
    @Test
    void processPotionFullHealthTest() {
        enemy = new Thief(2);
        enemy.setId(4L);
        enemy.setHealth(80);

        when(potion.usePotion()).thenReturn(30);

        doNothing().when(enemyService).updatePotionCountById(1, 4L);
        doNothing().when(enemyService).updateHealthById(90, 4L);
        when(battleHistoryMessageService.createNewMessage(anyLong(),anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        enemyMoveService.processPotion(enemy, 1L, hero);

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
    }
    @Test
    void getDamageMessageMissedTest() {
        String result = enemyMoveService.getDamageMessage("Move", 0);
        Assertions.assertEquals(result, "Move missed!");
    }
    @Test
    void getDamageMessageHitTest() {
        String result = enemyMoveService.getDamageMessage("Move", 1);
        Assertions.assertEquals(result, "Move did 1 damage.");
    }
    @Test
    void processEnemyMoveTest() {
        enemy = new Thief(2);
        hero = new Tank("Name");
        hero.setId(5L);

        doNothing().when(heroService).updateLossCountById(5L, 1);
        doNothing().when(heroService).updateHealthById(0, 5L);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        enemyMoveService.processEnemyMove(1000, enemy, 1L, hero, "Move");

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
    }
    @Test
    void processEnemyMoveElseTest() {
        enemy = new Thief(2);
        hero = new Tank("Name");
        hero.setId(5L);

        doNothing().when(heroService).updateHealthById(119, 5L);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        enemyMoveService.processEnemyMove(1, enemy, 1L, hero, "Move");

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
    }
    @Test
    void processStealTrueTest(){
        hero = new Tank("Name");
        hero.setId(5L);
        enemy = new Thief(2);
        enemy.setId(4L);
        enemy.setPotions(1);
        when(steal.useSteal()).thenReturn(true);
        doNothing().when(heroService).updatePotionCountById(2, 5L);
        doNothing().when(enemyService).updatePotionCountById(2, 4L);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
        enemyMoveService.processSteal(enemy, 1L, hero);
        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
    }
    @Test
    void processStealFalseTest(){
        hero = new Tank("Name");
        hero.setId(5L);
        enemy = new Thief(2);
        enemy.setId(4L);
        enemy.setPotions(1);
        when(steal.useSteal()).thenReturn(false);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
        enemyMoveService.processSteal(enemy, 1L, hero);
        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
    }
    @Test
    void processStealMaxPotionsTest(){
        hero = new Tank("Name");
        hero.setId(5L);
        hero.setPotions(0);
        enemy = new Thief(2);
        enemy.setId(4L);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
        enemyMoveService.processSteal(enemy, 1L, hero);
        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
    }

}