//package com.battlejawn.Service;
//
//import com.battlejawn.DTO.EnemyMoveDTO;
//import com.battlejawn.DTO.HeroMoveDTO;
//import com.battlejawn.EnemyMove.EnemySteal;
//import com.battlejawn.Entities.Battle.BattleHistoryMessage;
//import com.battlejawn.Entities.Battle.BattleSession;
//import com.battlejawn.Entities.Battle.BattleStatus;
//import com.battlejawn.Entities.Enemy.*;
//import com.battlejawn.Entities.Hero.DPS;
//import com.battlejawn.Entities.Hero.Hero;
//import com.battlejawn.Entities.Hero.Tank;
//import com.battlejawn.Entities.Inventory;
//import com.battlejawn.HeroMove.Heal.Potion;
//import com.battlejawn.Randomizer.Randomizer;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class EnemyMoveServiceTest {
//    @Mock
//    HeroService heroService;
//    @Mock
//    Hero hero;
//    @Mock
//    BattleHistoryMessageService battleHistoryMessageService;
//    @Mock
//    EnemyService enemyService;
//    @Mock
//    Enemy enemy;
//    @Mock
//    Randomizer randomizer;
//    @Mock
//    BattleSession battleSession;
//    @Mock
//    EnemySteal enemySteal;
//    @Mock
//    InventoryService inventoryService;
//    @Mock
//    BattleSessionService battleSessionService;
//    @Mock
//    BattleHistoryMessage battleHistoryMessage;
//    @Mock
//    List<String> battleHistoryMessageList;
//    @Mock
//    Potion potion;
//    @Mock
//    Inventory inventory;
//    @Mock
//    BattleStatusService battleStatusService;
//    @Mock
//    BattleStatus battleStatus;
//    @InjectMocks
//    EnemyMoveService enemyMoveService;
//    @BeforeEach
//    void setup() {
//        inventory = new Inventory();
//        battleSession = new BattleSession();
//        battleSession.setId(3L);
//        battleSession.setEnemyId(1L);
//        battleSession.setHeroId(2L);
//        hero = new Tank("Name");
//        battleStatus = new BattleStatus();
//        battleSession.setBattleStatus(battleStatus);
//    }
//    @Test
//    void enemyMoveWolfBiteTest() {
//        enemy = new Wolf(2, 55, 12);
//
//        when(randomizer.getRandomInt(9)).thenReturn(4);
//
//        when(battleStatusService.findBattleStatusById(anyLong())).thenReturn(battleStatus);
//        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
//        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
//        when(heroService.getHeroById(anyLong())).thenReturn(hero);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.enemyMove(anyLong());
//        assertNotNull(enemyMoveDTO);
//    }
//    @Test
//    void enemyMoveWolfMaimTest() {
//        enemy = new Wolf(2, 55, 12);
//
//        when(randomizer.getRandomInt(9)).thenReturn(1);
//
//        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
//        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
//        when(heroService.getHeroById(anyLong())).thenReturn(hero);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.enemyMove(anyLong());
//        assertNotNull(enemyMoveDTO);
//    }
//    @Test
//    void enemyMoveSpiritShadowBlastTest() {
//
//        enemy = new Spirit(2, 157, 25);
//        when(randomizer.getRandomInt(9)).thenReturn(4);
//        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
//        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
//        when(heroService.getHeroById(anyLong())).thenReturn(hero);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.enemyMove(anyLong());
//        assertNotNull(enemyMoveDTO);
//    }
//    @Test
//    void enemyMoveSpiritSoulEaterTest() {
//
//        enemy = new Spirit(2, 157, 25);
//        when(randomizer.getRandomInt(9)).thenReturn(0);
//        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
//        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
//        when(heroService.getHeroById(anyLong())).thenReturn(hero);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.enemyMove(anyLong());
//        assertNotNull(enemyMoveDTO);
//    }
//    @Test
//    void enemyMoveOrcPotionTest() {
//
//        enemy = new Orc(2, 105, 3, 25);
//        enemy.setHealth(1);
//        when(randomizer.getRandomInt(9)).thenReturn(1);
//        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
//        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
//        when(heroService.getHeroById(anyLong())).thenReturn(hero);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.enemyMove(anyLong());
//        assertNotNull(enemyMoveDTO);
//    }
//    @Test
//    void enemyMoveOrcSecondPotionTest() {
//
//        enemy = new Orc(2, 105, 3, 25);
//        enemy.setHealth(41);
//        when(randomizer.getRandomInt(9)).thenReturn(9);
//        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
//        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
//        when(heroService.getHeroById(anyLong())).thenReturn(hero);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.enemyMove(anyLong());
//        assertNotNull(enemyMoveDTO);
//    }
//    @Test
//    void enemyMoveOrcStrikeTest() {
//
//        enemy = new Orc(2, 105, 3, 25);
//        enemy.setHealth(41);
//        when(randomizer.getRandomInt(9)).thenReturn(7);
//        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
//        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
//        when(heroService.getHeroById(anyLong())).thenReturn(hero);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.enemyMove(anyLong());
//        assertNotNull(enemyMoveDTO);
//    }
//    @Test
//    void enemyMoveOrcImpaleTest() {
//
//        enemy = new Orc(2, 105, 3, 25);
//        enemy.setHealth(41);
//        when(randomizer.getRandomInt(9)).thenReturn(1);
//        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
//        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
//        when(heroService.getHeroById(anyLong())).thenReturn(hero);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.enemyMove(anyLong());
//        assertNotNull(enemyMoveDTO);
//    }
//    @Test
//    void enemyMoveThiefPotionTest() {
//
//        enemy = new Thief(2, 95, 2, 4, 20);
//        enemy.setHealth(1);
//        when(randomizer.getRandomInt(9)).thenReturn(1);
//        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
//        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
//        when(heroService.getHeroById(anyLong())).thenReturn(hero);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.enemyMove(anyLong());
//        assertNotNull(enemyMoveDTO);
//    }
//    @Test
//    void enemyMoveThiefSecondPotionTest() {
//
//        enemy = new Thief(2, 95, 2, 4, 20);
//        enemy.setHealth(31);
//        when(randomizer.getRandomInt(9)).thenReturn(10);
//        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
//        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
//        when(heroService.getHeroById(anyLong())).thenReturn(hero);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.enemyMove(anyLong());
//        assertNotNull(enemyMoveDTO);
//    }
//    @Test
//    void enemyMoveThiefStabTest() {
//
//        enemy = new Thief(2, 95, 2, 4, 20);
//        enemy.setHealth(31);
//        when(randomizer.getRandomInt(9)).thenReturn(2);
//        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
//        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
//        when(heroService.getHeroById(anyLong())).thenReturn(hero);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.enemyMove(anyLong());
//        assertNotNull(enemyMoveDTO);
//    }
//
//    @Test
//    void enemyMoveStealTest(){
//        enemy = new Thief(2, 95, 2, 4, 20);
//        enemy.setHealth(31);
//        when(randomizer.getRandomInt(9)).thenReturn(1);
//        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
//        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
//        when(heroService.getHeroById(anyLong())).thenReturn(hero);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.enemyMove(anyLong());
//        assertNotNull(enemyMoveDTO);
//    }
//    @Test
//    void enemyMoveThiefStealTest() {
//        DPS dps = new DPS("DPS");
//        enemy = new Thief(2, 95, 2, 4, 20);
//        enemy.setName("Thief");
//
//        when(inventoryService.findItemCount(inventory, "Potion")).thenReturn(1);
//        when(enemySteal.useSteal()).thenReturn(true);
//        doNothing().when(inventoryService).removeFirstFromInventory(anyLong(),anyString());
//        doNothing().when(heroService).updateHero(any());
//        doNothing().when(enemyService).updatePotionCountById(3,null);
//        when(battleHistoryMessageService.createNewMessage(anyLong(),anyString())).thenReturn(battleHistoryMessage);
//        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.processEnemySteal(enemy, 1L, dps);
//        assertNotNull(enemyMoveDTO);
//
//    }
//
//    @Test
//    void enemyMoveThiefStealFailTest() {
//        DPS dps = new DPS("DPS");
//        Enemy thief = new Thief(2, 95, 2, 4, 20);
//        thief.setName("Thief");
//
//        when(inventoryService.findItemCount(inventory, "Potion")).thenReturn(1);
//        when(enemySteal.useSteal()).thenReturn(false);
//        when(battleHistoryMessageService.createNewMessage(anyLong(),anyString())).thenReturn(battleHistoryMessage);
//        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.processEnemySteal(thief, 1L, dps);
//        assertNotNull(enemyMoveDTO);
//
//    }
//
//    @Test
//    void enemyMoveThiefStealNoPotionsTest() {
//        enemy = new Thief(2, 95, 0, 4, 20);
//        enemy.setName("Thief");
//
//        when(battleHistoryMessageService.createNewMessage(anyLong(),anyString())).thenReturn(battleHistoryMessage);
//        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.processEnemySteal(enemy, 1L, hero);
//        assertNotNull(enemyMoveDTO);
//
//    }
//    @Test
//    void enemyMoveNullEnemyNameTest() {
//
//        enemy = new Thief(2, 95, 2, 4, 20);
//        enemy.setName("Name");
//        when(randomizer.getRandomInt(9)).thenReturn(12);
//        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
//        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
//        when(heroService.getHeroById(anyLong())).thenReturn(hero);
//
//        EnemyMoveDTO enemyMoveDTO = enemyMoveService.enemyMove(anyLong());
//        assertNull(enemyMoveDTO);
//    }
//    @Test
//    void processPotionTest() {
//        enemy = new Thief(2, 95, 2, 4, 20);
//        enemy.setId(4L);
//        enemy.setHealth(30);
//
//        when(potion.usePotion()).thenReturn(30);
//
//        doNothing().when(enemyService).updatePotionCountById(1, 4L);
//        doNothing().when(enemyService).updateHealthById(60, 4L);
//        when(battleHistoryMessageService.createNewMessage(anyLong(),anyString())).thenReturn(battleHistoryMessage);
//        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
//
//        enemyMoveService.processPotion(enemy, 1L, hero);
//
//        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
//    }
//    @Test
//    void processPotionFullHealthTest() {
//        enemy = new Thief(2, 95, 2, 4, 20);
//        enemy.setId(4L);
//        enemy.setHealth(80);
//
//        when(potion.usePotion()).thenReturn(30);
//
//        doNothing().when(enemyService).updatePotionCountById(1, 4L);
//        doNothing().when(enemyService).updateHealthById(95, 4L);
//        when(battleHistoryMessageService.createNewMessage(anyLong(),anyString())).thenReturn(battleHistoryMessage);
//        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
//
//        enemyMoveService.processPotion(enemy, 1L, hero);
//
//        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
//    }
//    @Test
//    void getDamageMessageMissedTest() {
//        String result = enemyMoveService.getDamageMessage("Move", 0);
//        Assertions.assertEquals(result, "Move missed!");
//    }
//    @Test
//    void getDamageMessageHitTest() {
//        String result = enemyMoveService.getDamageMessage("Move", 1);
//        Assertions.assertEquals(result, "Move did 1 damage.");
//    }
//
//    @Test
//    void isBlockingTest() {
//        BattleStatus blockingStatus = new BattleStatus();
//        blockingStatus.setHeroBlocking(true);
//        BattleSession blockingSession = new BattleSession();
//        blockingStatus.setBattleSession(blockingSession);
//        blockingSession.setBattleStatus(blockingStatus);
//
//        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(blockingSession);
//        doNothing().when(heroService).updateHero(any());
//        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
//        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
//
//        enemyMoveService.processEnemyMove(1, enemy, 3L, hero, "Bite");
//
//        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(3L);
//    }
//
//    @Test
//    void defeatHeroTest() {
//        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
//        doNothing().when(heroService).updateHero(any());
//        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
//        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
//        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
//
//        enemyMoveService.processEnemyMove(1000, enemy, 1L, hero, "Bite");
//        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(1L);
//    }
////    @Test
////    void processEnemyMoveTest() {
////        enemy = new Thief(2, 95, 2, 4, 20);
////        hero = new Tank("Name");
////        hero.setId(5L);
////
////        doNothing().when(heroService).updateHero(hero);
////        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
////        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
////
////        enemyMoveService.processEnemyMove(1000, enemy, 1L, hero, "Move");
////
////        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
////    }
////    @Test
////    void processEnemyMoveElseTest() {
////        enemy = new Thief(2, 95, 2, 4, 20);
////        hero = new Tank("Name");
////        hero.setId(5L);
////
////        doNothing().when(heroService).updateHero(hero);
////        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
////        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
////
////        enemyMoveService.processEnemyMove(1, enemy, 1L, hero, "Move");
////
////        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
////    }
//
//}