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
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Repository.HeroRepository;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {

    @InjectMocks
    HeroService heroService;

    @Mock
    HeroRepository heroRepository;

    @Test
    void testGetHeroByIdReturnsCorrectHero() {

        Long id = 1L;
        Tank tank = new Tank();
        tank.setId(id);

        Mockito.when(heroRepository.findById(id)).thenReturn(Optional.of(tank));

        Hero actual = heroService.getHeroById(id);

        assertEquals(actual.getId(), id);
    }

    // @Test
    // void testGetHeroHealthByIdReturnsCorrectHealth() {
    //     Long id = 1L;
    //     int health = 50;
    //     Tank tank = new Tank();
    //     tank.setId(id);
    //     tank.setHealth(health);

    //     Mockito.when(heroRepository.findById(id)).thenReturn(tank);
    // }
}
