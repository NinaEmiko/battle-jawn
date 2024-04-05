package com.battlejawn.Service;

import com.battlejawn.Entities.Enemy.*;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Hero.Tank;
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
    List<Long> heroExperience;
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
    }
    @Test
    void processExperienceHeroLevelsTest() {
        hero.setExperience(51L);
        enemy = new Wolf(1);
        doNothing().when(heroService).updateHero(any());
        String result = experienceProcessorService.processExperience(hero, enemy, "Hero wins");
        Assertions.assertEquals(result, "Congratulations! You've reached level 2!");
    }
    @Test
    void processExperienceHeroWinsTest() {
        enemy = new Wolf(1);
        doNothing().when(heroService).updateHero(any());
        String result = experienceProcessorService.processExperience(hero, enemy, "Hero wins");
        Assertions.assertEquals(result, "You've gained 15 experience!");
    }
    @Test
    void processExperienceHeroLosesTest() {
        enemy = new Wolf(1);
        doNothing().when(heroService).updateHero(any());
        String result = experienceProcessorService.processExperience(hero, enemy, "Hero loses");
        Assertions.assertEquals(result, "You've lost 15 experience.");
    }
    @Test
    void processExperienceHeroLevel10Test() {
        hero.setLevel(10);
        enemy = new Wolf(1);
        doNothing().when(heroService).updateHero(any());
        String result = experienceProcessorService.processExperience(hero, enemy, "Hero wins");
        Assertions.assertEquals(result, "You've gained 0 experience!");
    }
    @Test
    void processExperienceOrcTest() {
        enemy = new Orc(1);
        for (int i = 1; i <= 10; i++) {
            enemy.setLevel(i);
            doNothing().when(heroService).updateHero(any());
            experienceProcessorService.processExperience(hero, enemy, "Hero wins");
            verify(heroService, times(i)).updateHero(any());
        }
    }
    @Test
    void processExperienceSpiritTest() {
        enemy = new Spirit(1);
        for (int i = 1; i <= 10; i++) {
            enemy.setLevel(i);
            doNothing().when(heroService).updateHero(any());
            experienceProcessorService.processExperience(hero, enemy, "Hero wins");
            verify(heroService, times(i)).updateHero(any());
        }
    }
    @Test
    void processExperienceThiefTest() {
        enemy = new Thief(1);
        for (int i = 1; i <= 10; i++) {
            enemy.setLevel(i);
            doNothing().when(heroService).updateHero(any());
            experienceProcessorService.processExperience(hero, enemy, "Hero wins");
            verify(heroService, times(i)).updateHero(any());
        }
    }
    @Test
    void processExperienceWolfTest() {
        enemy = new Wolf(1);
        for (int i = 1; i <= 10; i++) {
            enemy.setLevel(i);
            doNothing().when(heroService).updateHero(any());
            experienceProcessorService.processExperience(hero, enemy, "Hero wins");
            verify(heroService, times(i)).updateHero(any());
        }
    }

    @Test
    void determineLevelTest(){
        enemy = new Orc(1);
        for (int i = 1; i <= 10; i++) {
            hero.setLevel(i);
            enemy.setLevel(i);
            hero.setExperience(heroExperience.get(i));
            doNothing().when(heroService).updateHero(any());
            experienceProcessorService.processExperience(hero, enemy, "Hero wins");
            verify(heroService, times(i)).updateHero(any());
        }
    }
    @Test
    void calculateExperienceLossTest(){
        enemy = new Orc(1);
        for (int i = 1; i <= 10; i++) {
            hero.setLevel(i);
            enemy.setLevel(i);
            hero.setExperience(heroExperience.get(i));
            doNothing().when(heroService).updateHero(any());
            experienceProcessorService.processExperience(hero, enemy, "Hero loses");
            verify(heroService, times(i)).updateHero(any());
        }
    }
}
