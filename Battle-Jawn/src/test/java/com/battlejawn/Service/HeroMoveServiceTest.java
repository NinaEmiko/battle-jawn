package com.battlejawn.Service;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Enemy.Orc;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.HeroMove.Run;
import com.battlejawn.HeroMove.Steal;
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
    HeroService heroService;
    @Mock
    EnemyService enemyService;
    @Mock
    HeroMoveDTO heroMoveDTO;
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
    BattleHistoryMessage battleHistoryMessage;
    @Mock
    List<String> battleHistoryMessageList;
    @InjectMocks
    HeroMoveService heroMoveService;
    @BeforeEach
    void setup(){
        battleHistoryMessageList = new ArrayList<>();
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
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Potion", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveStealTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);

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
        doNothing().when(heroService).updateRunCountById(anyLong(), anyInt());
        when(battleHistoryMessageService.createNewMessage(anyLong(), any())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
        HeroMoveDTO heroMoveDTO = heroMoveService.processRun(enemy, 1L, hero);
        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveStealSuccessTest() {

    }
}