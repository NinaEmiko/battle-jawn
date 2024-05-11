package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Enemy.Wolf;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Repository.BattleSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BattleSessionServiceTest {

    @Mock
    BattleSessionRepository battleSessionRepository;
    @Mock
    EnemyService enemyService;
    @Mock
    ExperienceProcessorService experienceProcessorService;
    @Mock
    BattleHistoryMessageService battleHistoryMessageService;
    @Mock
    BattleHistoryMessage battleHistoryMessage;
    @Mock
    BattleSession battleSession;
    @Mock
    Enemy enemy;
    @Mock
    Hero hero;
    @Mock
    HeroService heroService;
    @InjectMocks
    BattleSessionService battleSessionService;
    @BeforeEach
    void setup() {
        hero = new Tank();
        battleSession = new BattleSession();
        battleHistoryMessage = new BattleHistoryMessage();
        battleSession.setId(4L);
        enemy = new Wolf(2, 55, 12);
        hero.setId(1L);
        enemy.setId(2L);

    }
    @Test
    void getBattleSessionByIdTest() {
        when(battleSessionRepository.findById(anyLong())).thenReturn(Optional.of(battleSession));
        battleSessionService.getBattleSessionById(anyLong());
        verify(battleSessionRepository, times(1)).findById(anyLong());
    }
    @Test
    void getBattleSessionByIdExceptionTest() {
        when(battleSessionRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> battleSessionService.getBattleSessionById(anyLong()));
    }

    @Test
    void createNewBattleSessionTest() {
        Tank tank = new Tank("Name");
        tank.setLevel(1);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        when(enemyService.createNewEnemy(anyInt())).thenReturn(enemy);
        when(battleSessionRepository.save(any())).thenReturn(battleSession);
        when(battleHistoryMessageService.createNewMessage(null, "You encountered an enemy!")).thenReturn(battleHistoryMessage);
        doNothing().when(heroService).updateHero(any());
        battleSessionService.createNewBattleSession(1L);
        verify(battleSessionRepository, times(1)).save(any());
    }
    @Test
    void createNewBattleSessionExceptionTest() {
        assertThrows(RuntimeException.class, () -> battleSessionService.createNewBattleSession(anyLong()));
    }

    @Test
    void processEndOfBattleTest() {
        battleSession.setId(1L);
        when(battleSessionRepository.findById(anyLong())).thenReturn(Optional.of(battleSession));
        when(heroService.getHeroById(null)).thenReturn(hero);
        when(enemyService.getEnemyById(null)).thenReturn(enemy);
        when(experienceProcessorService.processExperience(any(), any(), anyString())).thenReturn("Result");
        doNothing().when(heroService).updateHero(any());
        battleSessionService.processEndOfBattle(1L, "Thing");
        verify(experienceProcessorService, times(1)).processExperience(any(),any(),anyString());
    }
}