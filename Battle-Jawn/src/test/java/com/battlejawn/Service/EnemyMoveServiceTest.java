package com.battlejawn.Service;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.*;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Hero.Tank;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    BattleSession battleSession;
    @Mock
    BattleSessionService battleSessionService;
    @Mock
    Math math;
    @Mock
    HeroMoveDTO enemyMoveDTO;
    @InjectMocks
    EnemyMoveService enemyMoveService;
    @BeforeEach
    void setup() {
        battleSession = new BattleSession();
        battleSession.setId(3L);
        battleSession.setEnemyId(1L);
        battleSession.setHeroId(2L);

        Enemy enemy = new Orc(1);
        Hero hero = new Tank();
    }
    @Test
    void enemyMoveTest() {

    }
    @Test
    void enemyMoveWolfTest() {

//        when(enemyMoveService.getRandomIndex()).thenReturn(4);

        enemy = new Wolf(1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }

    @Test
    void enemyMoveSpiritTest() {

        enemy = new Spirit(1);
//        when(enemyMoveService.getRandomIndex()).thenReturn(4);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }

    @Test
    void enemyMoveOrcTest() {

        enemy = new Orc(1);
//        when(enemyMoveService.getRandomIndex()).thenReturn(4);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }

    @Test
    void enemyMoveThiefTest() {

        enemy = new Thief(1);
//        when(enemyMoveService.getRandomIndex()).thenReturn(4);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = enemyMoveService.enemyMove(anyLong());
        assertNotNull(heroMoveDTO);
    }

    @Test
    void getDamageMessageMissedTest() {
        String expected = "This move missed!";
        String actual = enemyMoveService.getDamageMessage("This move", 0);
        assertEquals(expected, actual);
    }
}