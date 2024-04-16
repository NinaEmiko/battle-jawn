package com.battlejawn.Service;

import com.battlejawn.Entities.Enemy.*;
import com.battlejawn.Entities.Hero.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExperienceProcessorServiceTest {
    @Mock
    HeroService heroService;
    @Mock
    Hero hero;
    @Mock
    Enemy enemy;
    @Mock
    CoinProcessorService coinProcessorService;
    List<Long> heroExperience;
    List<Long> heroExperienceBackwards;
    @InjectMocks
    ExperienceProcessorService experienceProcessorService;
    @BeforeEach
    void setUp() {
        hero = new Tank("Name");
        heroExperience = new ArrayList<>();
        heroExperience.add(5001L);
        heroExperience.add(5001L);
        heroExperience.add(3001L);
        heroExperience.add(2001L);
        heroExperience.add(1251L);
        heroExperience.add(751L);
        heroExperience.add(501L);
        heroExperience.add(301L);
        heroExperience.add(126L);
        heroExperience.add(51L);
        heroExperience.add(1L);

        heroExperienceBackwards = new ArrayList<>();
        heroExperienceBackwards.add(49L);
        heroExperienceBackwards.add(49L);
        heroExperienceBackwards.add(124L);
        heroExperienceBackwards.add(299L);
        heroExperienceBackwards.add(499L);
        heroExperienceBackwards.add(749L);
        heroExperienceBackwards.add(1249L);
        heroExperienceBackwards.add(1999L);
        heroExperienceBackwards.add(2999L);
        heroExperienceBackwards.add(4999L);
    }
    @Test
    void processExperienceHeroWinsTest() {
        enemy = new Wolf(1, 50, 10);
        when(coinProcessorService.processCoins(any())).thenReturn(1L);
        doNothing().when(heroService).updateHero(any());
        String result = experienceProcessorService.processExperience(hero, enemy, "Hero wins");
        Assertions.assertEquals(result, "You win! You've gained 15 experience! Enemy dropped 1 coins.");
    }
    @Test
    void processExperienceHeroRunsTest() {
        enemy = new Wolf(1, 50, 10);
        doNothing().when(heroService).updateHero(any());
        String result = experienceProcessorService.processExperience(hero, enemy, "Hero runs");
        Assertions.assertEquals(result, "You've gained 0 experience.");
    }
    @Test
    void processExperienceHeroLosesTest() {
        enemy = new Wolf(1, 50, 10);
        doNothing().when(heroService).updateHero(any());
        String result = experienceProcessorService.processExperience(hero, enemy, "Hero loses");
        Assertions.assertEquals(result, "You've lost 15 experience.");
    }
    @Test
    void processExperienceHeroLevel10Test() {
        hero.setLevel(10);
        enemy = new Wolf(1, 50, 10);
        when(coinProcessorService.processCoins(any())).thenReturn(1L);
        doNothing().when(heroService).updateHero(any());
        String result = experienceProcessorService.processExperience(hero, enemy, "Hero wins");
        Assertions.assertEquals(result, "You win! You've gained 0 experience! Enemy dropped 1 coins.");
    }
    @Test
    void processExperienceOrcTest() {
        enemy = new Orc(1, 100, 3, 20);
        for (int i = 1; i <= 10; i++) {
            enemy.setLevel(i);
            when(coinProcessorService.processCoins(any())).thenReturn(1L);
            doNothing().when(heroService).updateHero(any());
            experienceProcessorService.processExperience(hero, enemy, "Hero wins");
            verify(heroService, times(i)).updateHero(any());
        }
    }
    @Test
    void processExperienceSpiritTest() {
        enemy = new Spirit(1, 150, 20);
        for (int i = 1; i <= 10; i++) {
            enemy.setLevel(i);
            when(coinProcessorService.processCoins(any())).thenReturn(1L);
            doNothing().when(heroService).updateHero(any());
            experienceProcessorService.processExperience(hero, enemy, "Hero wins");
            verify(heroService, times(i)).updateHero(any());
        }
    }
    @Test
    void processExperienceThiefTest() {
        enemy = new Thief(1, 90, 2, 4, 17);
        for (int i = 1; i <= 10; i++) {
            enemy.setLevel(i);
            when(coinProcessorService.processCoins(any())).thenReturn(1L);
            doNothing().when(heroService).updateHero(any());
            experienceProcessorService.processExperience(hero, enemy, "Hero wins");
            verify(heroService, times(i)).updateHero(any());
        }
    }
    @Test
    void processExperienceWolfTest() {
        enemy = new Wolf(1, 50, 10);
        for (int i = 1; i <= 10; i++) {
            enemy.setLevel(i);
            when(coinProcessorService.processCoins(any())).thenReturn(1L);
            doNothing().when(heroService).updateHero(any());
            experienceProcessorService.processExperience(hero, enemy, "Hero wins");
            verify(heroService, times(i)).updateHero(any());
        }
    }

    @Test
    void determineLevelTest(){
        enemy = new Orc(1, 100, 3, 20);
        for (int i = 1; i <= 10; i++) {
            hero.setLevel(i);
            enemy.setLevel(i);
            when(coinProcessorService.processCoins(any())).thenReturn(1L);
            hero.setExperience(heroExperience.get(i));
            doNothing().when(heroService).updateHero(any());
            experienceProcessorService.processExperience(hero, enemy, "Hero wins");
            verify(heroService, times(i)).updateHero(any());
        }
    }
    @Test
    void calculateExperienceLossTest(){
        enemy = new Orc(1, 100, 3, 20);
        for (int i = 1; i <= 10; i++) {
            hero.setLevel(i);
            enemy.setLevel(i);
            hero.setExperience(heroExperience.get(i));
            doNothing().when(heroService).updateHero(any());
            experienceProcessorService.processExperience(hero, enemy, "Hero loses");
            verify(heroService, times(i)).updateHero(any());
        }
    }

    @Test
    void determineMaxHealthTankTest() {
        enemy = new Orc(1, 100, 3, 20);
        for (int i = 1; i <= 9; i++) {
            hero.setExperience(heroExperienceBackwards.get(i));
            hero.setLevel(i);
            enemy.setLevel(i);
            doNothing().when(heroService).updateHero(any());
            when(coinProcessorService.processCoins(any())).thenReturn(1L);
            experienceProcessorService.processExperience(hero, enemy, "Hero wins");
            verify(heroService, times(i)).updateHero(any());
        }
    }
    @Test
    void determineMaxHealthHealerTest() {
        hero = new Healer("Name");
        enemy = new Orc(1, 100, 3, 20);
        for (int i = 1; i <= 9; i++) {
            hero.setExperience(heroExperienceBackwards.get(i));
            hero.setLevel(i);
            enemy.setLevel(i);
            doNothing().when(heroService).updateHero(any());
            when(coinProcessorService.processCoins(any())).thenReturn(1L);
            experienceProcessorService.processExperience(hero, enemy, "Hero wins");
            verify(heroService, times(i)).updateHero(any());
        }
    }
    @Test
    void determineMaxHealthDPSTest() {
        hero = new DPS("Name");
        enemy = new Orc(1, 100, 3, 20);
        for (int i = 1; i <= 9; i++) {
            hero.setExperience(heroExperienceBackwards.get(i));
            hero.setLevel(i);
            enemy.setLevel(i);
            doNothing().when(heroService).updateHero(any());
            when(coinProcessorService.processCoins(any())).thenReturn(1L);
            experienceProcessorService.processExperience(hero, enemy, "Hero wins");
            verify(heroService, times(i)).updateHero(any());
        }
    }
    @Test
    void determineMaxHealthCasterTest() {
        hero = new Caster("Name");
        enemy = new Orc(1, 100, 3, 20);
        for (int i = 1; i <= 9; i++) {
            hero.setExperience(heroExperienceBackwards.get(i));
            hero.setLevel(i);
            enemy.setLevel(i);
            doNothing().when(heroService).updateHero(any());
            when(coinProcessorService.processCoins(any())).thenReturn(1L);
            experienceProcessorService.processExperience(hero, enemy, "Hero wins");
            verify(heroService, times(i)).updateHero(any());
        }
    }
    @Test
    void determineMaxHealthInvalidRoleTest(){
        hero = new Caster("Name");
        hero.setRole("No");
        enemy = new Orc(1, 100, 3, 20);
        hero.setExperience(49L);
        hero.setLevel(1);
        enemy.setLevel(1);
        doNothing().when(heroService).updateHero(any());
        when(coinProcessorService.processCoins(any())).thenReturn(1L);
        experienceProcessorService.processExperience(hero, enemy, "Hero wins");
        verify(heroService, times(1)).updateHero(any());
    }
}
