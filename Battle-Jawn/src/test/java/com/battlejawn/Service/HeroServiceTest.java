package com.battlejawn.Service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.battlejawn.Entities.UserAccount;
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

        when(heroRepository.findAll()).thenReturn(heroes);
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
        when(heroRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> heroService.getHeroById(anyLong()));
    }
    @Test
    void getHeroHealthByIdTest() {
        when(heroRepository.findById(anyLong())).thenReturn(Optional.of(hero));
        heroService.getHeroHealthById(anyLong());
        verify(heroRepository, times(1)).findById(anyLong());
    }
    @Test
    void getHeroHealthByIdExceptionTest() {
        when(heroRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> heroService.getHeroHealthById(anyLong()));

    }
    @Test
    void getHeroListByAccountIdTest() {
        when(heroRepository.findByUserAccountId(anyLong())).thenReturn(heroes);
        heroService.getHeroListByAccountId(anyLong());
        verify(heroRepository, times(1)).findByUserAccountId(anyLong());
    }
    @Test
    void getHeroListByAccountIdExceptionTest() {
        when(heroRepository.findByUserAccountId(anyLong())).thenReturn(null);
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
        when(heroRepository.findByWinCount()).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> heroService.getHeroListByWinCount());
    }
    @Test
    void restHeroByIdTest() {
        when(heroRepository.findById(anyLong())).thenReturn(Optional.of(hero));
        heroService.restHeroById(anyLong());
        verify(heroRepository, times(1)).findById(anyLong());
    }
    @Test
    void deleteHeroByIdTest() {
        doNothing().when(heroRepository).deleteById(anyLong());
        when(heroRepository.existsById(anyLong())).thenReturn(true);
        heroService.deleteHeroById(anyLong());
        verify(heroRepository, times(1)).deleteById(anyLong());
    }
    @Test
    void deleteHeroByIdExceptionTest() {
        when(heroRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> heroService.deleteHeroById(anyLong()));
    }
    @Test
    void saveHeroTankTest() {
        Long id = 1L;
        UserAccount userAccount = new UserAccount();
        userAccount.setId(id);

        when(userAccountRepository.findById(anyLong())).thenReturn(Optional.of(userAccount));
        when(heroRepository.save(any())).thenReturn(hero);

        heroService.saveHero("Tank", "Name", 1L);

        verify(userAccountRepository, times(1)).findById(anyLong());
        verify(heroRepository, times(1)).save(any());
    }
    @Test
    void saveHeroDPSTest() {
        Long id = 1L;
        UserAccount userAccount = new UserAccount();
        userAccount.setId(id);

        when(userAccountRepository.findById(anyLong())).thenReturn(Optional.of(userAccount));
        when(heroRepository.save(any())).thenReturn(hero);

        heroService.saveHero("DPS", "Name", 1L);

        verify(userAccountRepository, times(1)).findById(anyLong());
        verify(heroRepository, times(1)).save(any());
    }

    @Test
    void saveHeroCasterTest() {
        Long id = 1L;
        UserAccount userAccount = new UserAccount();
        userAccount.setId(id);

        when(userAccountRepository.findById(anyLong())).thenReturn(Optional.of(userAccount));
        when(heroRepository.save(any())).thenReturn(hero);

        heroService.saveHero("Caster", "Name", 1L);

        verify(userAccountRepository, times(1)).findById(anyLong());
        verify(heroRepository, times(1)).save(any());
    }

    @Test
    void saveHeroHealerTest() {
        Long id = 1L;
        UserAccount userAccount = new UserAccount();
        userAccount.setId(id);

        when(userAccountRepository.findById(anyLong())).thenReturn(Optional.of(userAccount));
        when(heroRepository.save(any())).thenReturn(hero);

        heroService.saveHero("Healer", "Name", 1L);

        verify(userAccountRepository, times(1)).findById(anyLong());
        verify(heroRepository, times(1)).save(any());
    }
    @Test
    void saveHeroExceptionTest() {
        Long id = 1L;
        UserAccount userAccount = new UserAccount();
        userAccount.setId(id);

        when(userAccountRepository.findById(anyLong())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> heroService.saveHero("Healer", "Name", 1L));
    }

    @Test
    void updateHeroTest() {
        when(heroRepository.save(any())).thenReturn(hero);
        heroService.updateHero(hero);
        verify(heroRepository, times(1)).save(any());
    }
}
