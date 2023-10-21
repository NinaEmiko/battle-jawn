package com.battlejawn.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Entities.Hero.Toon;
import com.battlejawn.Repository.ToonRepository;

@ExtendWith(MockitoExtension.class)
public class ToonServiceTest {

    @InjectMocks
    ToonService toonService;

    @Mock
    ToonRepository toonRepository;

    @Test
    void testGetToonById() {

        Long id = 1L;
        Tank tank = new Tank();
        tank.setId(id);

        Mockito.when(toonRepository.findById(id)).thenReturn(Optional.of(tank));

        Toon result = toonService.getToonById(id);

        assertEquals(result.getId(), id);
    }
}
