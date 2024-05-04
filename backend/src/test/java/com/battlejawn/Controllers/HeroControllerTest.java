package com.battlejawn.Controllers;

import com.battlejawn.Config.JsonParser;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Service.HeroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HeroControllerTest {
    @Mock
    HeroService heroService;
    @Mock
    List<Hero> heroes;
    @Mock
    Hero hero;
    @Mock
    JsonParser jsonParser;
    @InjectMocks
    HeroController heroController;
    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    void setup(){

    }
    @Test
    void getAllHeroesTest(){
        when(heroService.getAllHeroes()).thenReturn(heroes);
        heroController.getAllHeroes();
        verify(heroService, times(1)).getAllHeroes();
    }
    @Test
    void getAllHeroesNullTest(){
        when(heroService.getAllHeroes()).thenReturn(null);
        heroController.getAllHeroes();
        verify(heroService, times(1)).getAllHeroes();
    }
    @Test
    void getHeroByIdTest(){
        when(heroService.getHeroById(anyLong())).thenReturn(hero);
        heroController.getHeroById(anyLong());
        verify(heroService, times(1)).getHeroById(anyLong());
    }
    @Test
    void getHeroByIdNullTest(){
        when(heroService.getHeroById(anyLong())).thenReturn(null);
        heroController.getHeroById(anyLong());
        verify(heroService, times(1)).getHeroById(anyLong());
    }
    @Test
    void getHealthByIdTest(){
        when(heroService.getHeroHealthById(anyLong())).thenReturn(1);
        heroController.getHealthById(anyLong());
        verify(heroService, times(1)).getHeroHealthById(anyLong());
    }
    @Test
    void getHealthByIdNullTest(){
        when(heroService.getHeroHealthById(anyLong())).thenReturn(null);
        heroController.getHealthById(anyLong());
        verify(heroService, times(1)).getHeroHealthById(anyLong());
    }
    @Test
    void getHeroListByHighScoreTest() {
        when(heroService.getHeroListByHighScore()).thenReturn(heroes);
        heroController.getHeroListByHighScore();
        verify(heroService, times(1)).getHeroListByHighScore();
    }
    @Test
    void getHeroListByHighScoreNullTest() {
        when(heroService.getHeroListByHighScore()).thenReturn(null);
        heroController.getHeroListByHighScore();
        verify(heroService, times(1)).getHeroListByHighScore();
    }
    @Test
    void getHeroListByAccountIdTest(){
        when(heroService.getHeroListByAccountId(anyLong())).thenReturn(heroes);
        heroController.getHeroListByAccountId(anyLong());
        verify(heroService, times(1)).getHeroListByAccountId(anyLong());
    }
    @Test
    void getHeroListByAccountIdNullTest(){
        when(heroService.getHeroListByAccountId(anyLong())).thenReturn(null);
        heroController.getHeroListByAccountId(anyLong());
        verify(heroService, times(1)).getHeroListByAccountId(anyLong());
    }
    @Test
    void getHeroListByWinCountTest() {
        when(heroService.getHeroListByWinCount()).thenReturn(heroes);
        heroController.getHeroListByWinCount();
        verify(heroService, times(1)).getHeroListByWinCount();
    }
    @Test
    void getHeroListByWinCountNullTest() {
        when(heroService.getHeroListByWinCount()).thenReturn(null);
        heroController.getHeroListByWinCount();
        verify(heroService, times(1)).getHeroListByWinCount();
    }
    @Test
    public void createNewHeroTest() {
        hero = new Tank();
        when(heroService.saveHero(anyString(), anyString(), anyLong())).thenReturn(hero);
        when(jsonParser.extractHeroName(anyString())).thenReturn("This");
        when(jsonParser.extractRole(anyString())).thenReturn("That");
        when(jsonParser.extractUserAccountId(anyString())).thenReturn(1L);
        heroController.createNewHero(anyString());
        verify(heroService, times(1)).saveHero(anyString(), anyString(), anyLong());
    }
    @Test
    public void createNewHeroNullTest() {
        hero = new Tank();
        when(heroService.saveHero(anyString(), anyString(), anyLong())).thenReturn(null);
        when(jsonParser.extractHeroName(anyString())).thenReturn("This");
        when(jsonParser.extractRole(anyString())).thenReturn("That");
        when(jsonParser.extractUserAccountId(anyString())).thenReturn(1L);
        heroController.createNewHero(anyString());
        verify(heroService, times(1)).saveHero(anyString(), anyString(), anyLong());
    }

    @Test
    public void restHeroByIdTest() {
        when(heroService.restHeroById(anyLong())).thenReturn(hero);
        heroController.restHeroById(anyLong());
        verify(heroService, times(1)).restHeroById(anyLong());
    }
    @Test
    public void restHeroByIdNullTest() {
        when(heroService.restHeroById(anyLong())).thenReturn(null);
        heroController.restHeroById(anyLong());
        verify(heroService, times(1)).restHeroById(anyLong());
    }
    @Test
    public void deleteHeroByIdTest() {
        when(heroService.deleteHeroById(anyLong())).thenReturn("This");
        heroController.deleteHeroById(anyLong());
        verify(heroService, times(1)).deleteHeroById(anyLong());
    }
    @Test
    public void deleteHeroByIdNullTest() {
        when(heroService.deleteHeroById(anyLong())).thenReturn(null);
        heroController.deleteHeroById(anyLong());
        verify(heroService, times(1)).deleteHeroById(anyLong());
    }

}
