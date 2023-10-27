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
    void testGetToonByIdReturnsCorrectToon() {

        Long id = 1L;
        Tank tank = new Tank();
        tank.setId(id);

        Mockito.when(toonRepository.findById(id)).thenReturn(Optional.of(tank));

        Toon actual = toonService.getToonById(id);

        assertEquals(actual.getId(), id);
    }

    // @Test
    // void testGetToonHealthByIdReturnsCorrectHealth() {
    //     Long id = 1L;
    //     int health = 50;
    //     Tank tank = new Tank();
    //     tank.setId(id);
    //     tank.setHealth(health);

    //     Mockito.when(toonRepository.findById(id)).thenReturn(tank);
    // }
}
