package com.battlejawn.Purge;

import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Repository.BattleSessionRepository;
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
    BattleSession battleSession;
    @InjectMocks
    BattleSessionPurge battleSessionPurge;
    @BeforeEach
    void setup() {
        battleSession = new BattleSession();
        battleSessions = new ArrayList<>();
        battleSession.setBattleHistoryMessageId(1L);
        battleSession.setCreatedAt(LocalDateTime.now().minusDays(6));
        battleSessions.add(battleSession);
    }
    @Test
    void purgeBattleSessionTest() {
        when(battleSessionRepository.findAll()).thenReturn(battleSessions);
        doNothing().when(battleSessionRepository).deleteById(any());
        battleSessionPurge.purgeBattleSession();
        verify(battleSessionRepository, times(1)).deleteById(any());
    }
    @Test
    void purgeBattleSessionExceptionTest() {
        when(battleSessionRepository.findAll()).thenReturn(battleSessions);
        doThrow(new RuntimeException()).when(battleSessionRepository).deleteById(any());
        assertThrows(RuntimeException.class, () -> battleSessionPurge.purgeBattleSession());
    }
}
