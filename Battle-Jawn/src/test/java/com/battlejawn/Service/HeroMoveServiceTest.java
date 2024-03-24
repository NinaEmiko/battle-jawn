package com.battlejawn.Service;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
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
    @InjectMocks
    HeroMoveService heroMoveService;
    @BeforeEach
    void setup(){

    }
    @Test
    void heroMoveTest() {

    }
    @Test
    void getHeroMoveReturnObjectTest() {
        int expectedEnemyHealth = 1;
        int expectedHeroHealth = 2;
        int expectedPotionCount = 3;
        List<String> battleHistory = new ArrayList<>();
        String expectedBattleHistoryMessage = "Hi";
        battleHistory.add(expectedBattleHistoryMessage);
        boolean expectedGameOver = false;

        HeroMoveDTO actual = heroMoveService.getHeroMoveReturnObject(1, 2, 3, battleHistory, false);

        assertEquals(expectedEnemyHealth, actual.getEnemyHealth());
        assertEquals(expectedHeroHealth, actual.getHeroHealth());
        assertEquals(expectedPotionCount, actual.getPotionCount());
        assertEquals(expectedBattleHistoryMessage, actual.getBattleHistory().get(0));
        assertEquals(expectedGameOver, actual.isGameOver());

    }
}