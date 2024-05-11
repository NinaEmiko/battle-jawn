package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleStatus;
import com.battlejawn.Repository.BattleStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BattleStatusServiceTest {
    @Mock
    BattleStatus battleStatus;
    @Mock
    BattleStatusRepository battleStatusRepository;
    @InjectMocks
    BattleStatusService battleStatusService;
    @BeforeEach
    void setup() {

    }
    @Test
    void findBattleStatusByIdTest() {
        when(battleStatusRepository.findById(anyLong())).thenReturn(Optional.of(battleStatus));
        battleStatusService.findBattleStatusById(1L);
        verify(battleStatusRepository, times(1)).findById(anyLong());
    }
    @Test
    void findBattleStatusByIdExceptionTest() {
        when(battleStatusRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> battleStatusService.findBattleStatusById(1L));
    }
    @Test
    void saveBattleStatusTest() {
        when(battleStatusRepository.save(any())).thenReturn(battleStatus);
        battleStatusService.saveBattleStatus(battleStatus);
        verify(battleStatusRepository, times(1)).save(any());
    }
}
