package com.battlejawn.HeroMove.StrongAttack;


import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.HeroMove.Attack.Strike;
import com.battlejawn.Service.BattleHistoryMessageService;
import com.battlejawn.Service.BattleSessionService;
import com.battlejawn.Service.EnemyService;
import com.battlejawn.Service.HeroService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ImpaleTest {
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
    Tank tank;
    @Mock
    BattleHistoryMessage battleHistoryMessage;
    @Mock
    HeroMoveDTO heroMoveDTO;
    @InjectMocks
    Impale impale;

}
