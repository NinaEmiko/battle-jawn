package com.battlejawn.Purge;

import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Repository.BattleSessionRepository;
import com.battlejawn.Service.HeroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BattleSessionPurgeTest {
    @Mock
    BattleSessionRepository battleSessionRepository;
    @Mock
    List<BattleSession> battleSessions;
    @Mock
    HeroService heroService;
    @Mock
    Hero tank;
    @Mock
    BattleSession battleSession;
    @InjectMocks
    BattleSessionPurge battleSessionPurge;
    @BeforeEach
    void setup() {
        tank = new Tank("Name");
        battleSession = new BattleSession();
        battleSessions = new ArrayList<>();
        battleSession.setBattleHistoryMessageId(1L);
        battleSession.setCreatedAt(LocalDateTime.now().minusDays(6));
        battleSessions.add(battleSession);
    }
    @Test
    void purgeBattleSessionTest() {
        when(battleSessionRepository.findAll()).thenReturn(battleSessions);
        when(heroService.getHeroById(any())).thenReturn(tank);
        doNothing().when(heroService).updateHero(any());
        doNothing().when(battleSessionRepository).deleteById(any());
        battleSessionPurge.purgeBattleSession();
        verify(battleSessionRepository, times(1)).deleteById(any());
    }
    @Test
    void purgeBattleSessionExceptionTest() {
        when(heroService.getHeroById(any())).thenReturn(tank);
        doNothing().when(heroService).updateHero(any());
        when(battleSessionRepository.findAll()).thenReturn(battleSessions);
        doThrow(new RuntimeException()).when(battleSessionRepository).deleteById(any());
        assertThrows(RuntimeException.class, () -> battleSessionPurge.purgeBattleSession());
    }
}
