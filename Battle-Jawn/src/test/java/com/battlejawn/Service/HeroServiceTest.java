package com.battlejawn.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.battlejawn.Repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Repository.HeroRepository;

import javax.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {

    @InjectMocks
    HeroService heroService;

    @Mock
    HeroRepository heroRepository;
    @Mock
    UserAccountRepository userAccountRepository;
    @Mock
    List<Hero> heroes;
    @Mock
    Hero hero;

    @BeforeEach
    void setup() {
        hero = new Tank();
        heroes = new ArrayList<>();
    }

    @Test
    void getAllHeroesTest() {
        heroes.add(hero);
        when(heroRepository.findAll()).thenReturn(heroes);
        heroService.getAllHeroes();
        verify(heroRepository, times(1)).findAll();
    }
    @Test
    void getAllHeroesExceptionTest() {
        when(heroRepository.findAll()).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> heroService.getAllHeroes());
    }

    @Test
    void getHeroByIdTest() {
        when(heroRepository.findById(anyLong())).thenReturn(Optional.ofNullable(hero));
        heroService.getHeroById(anyLong());
        verify(heroRepository, times(1)).findById(anyLong());
    }
    @Test
    void getHeroByIdExceptionTest() {
        when(heroRepository.findById(anyLong())).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> heroService.getHeroById(anyLong()));
    }
//    @Test
//    void getHeroHealthByIdTest() {
//        when(heroRepository.findById(anyLong()).get().getHealth()).thenReturn(7);
//        int actual = heroService.getHeroHealthById(anyLong());
//        assertEquals(actual, 7);
//    }
    @Test
    void getHeroListByAccountIdTest() {
        when(heroRepository.findByUserAccountId(anyLong())).thenReturn(heroes);
        heroService.getHeroListByAccountId(anyLong());
        verify(heroRepository, times(1)).findByUserAccountId(anyLong());
    }
    @Test
    void getHeroListByAccountIdExceptionTest() {
        when(heroRepository.findByUserAccountId(anyLong())).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> heroService.getHeroListByAccountId(anyLong()));
    }
    @Test
    void getHeroListByWinCountTest() {
        when(heroRepository.findByWinCount()).thenReturn(heroes);
        heroService.getHeroListByWinCount();
        verify(heroRepository, times(1)).findByWinCount();
    }
    @Test
    void getHeroListByWinCountExceptionTest() {
        when(heroRepository.findByWinCount()).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> heroService.getHeroListByWinCount());
    }
}
