package com.battlejawn.Service;

import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Entities.Inventory;
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
public class StoreServiceTest {
    @Mock
    HeroService heroService;
    @Mock
    Hero hero;
    @Mock
    Inventory inventory;
    @InjectMocks
    StoreService storeService;
    @Mock
    InventoryService inventoryService;
    @BeforeEach
    void setup(){
        hero = new Tank("Name");
        inventory = new Inventory();
        hero.setInventory(inventory);
    }
    @Test
    void buyPotionSuccessTest() {
        hero.setCoins(10L);
        inventory.setId(2L);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);
        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(2);
        doNothing().when(heroService).updateHero(any());
        doNothing().when(inventoryService).addToFirstEmptySlot(any(), anyString());
        String jawn = storeService.buy(1L, "Potion", 1);
        verify(heroService, times(1)).updateHero(any());
        Assertions.assertEquals(jawn, "You purchased 1 potion.");
    }
    @Test
    void buyPotionFailTest() {
        hero.setCoins(100L);
        inventory.setId(2L);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);
        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(2);
        storeService.buy(1L, "Potion", 13);
        verify(heroService, times(0)).updateHero(any());
    }

    @Test
    void buyTwoPotionSuccessTest() {
        hero.setCoins(10L);
        inventory.setId(2L);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);
        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(2);
        doNothing().when(heroService).updateHero(any());
        doNothing().when(inventoryService).addToFirstEmptySlot(any(), anyString());
        String jawn = storeService.buy(1L, "Potion", 2);
        verify(heroService, times(1)).updateHero(any());
        Assertions.assertEquals(jawn, "You purchased 2 potions.");
    }
    @Test
    void buyTwoPotionInsufficientCoinsTest() {
        hero.setCoins(1L);
        inventory.setId(2L);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);
        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(2);
        storeService.buy(1L, "Potion", 2);
        verify(heroService, times(0)).updateHero(any());
    }

    @Test
    void buySwordSuccessTest() {
        hero.setCoins(10L);
        inventory.setId(2L);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);
        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(2);
        doNothing().when(heroService).updateHero(any());
        doNothing().when(inventoryService).addToFirstEmptySlot(any(), anyString());
        storeService.buy(1L, "Sword", 1);
        verify(heroService, times(1)).updateHero(any());
    }
    @Test
    void buyTwoSwordsSuccessTest() {
        hero.setCoins(10L);
        inventory.setId(2L);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);
        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(2);
        doNothing().when(heroService).updateHero(any());
        doNothing().when(inventoryService).addToFirstEmptySlot(any(), anyString());
        storeService.buy(1L, "Sword", 2);
        verify(heroService, times(1)).updateHero(any());
    }
    @Test
    void buyTwoSwordsInsufficientCoinsTest() {
        hero.setCoins(1L);
        inventory.setId(2L);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);
        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(2);
        storeService.buy(1L, "Sword", 2);
        verify(heroService, times(0)).updateHero(any());
    }

    @Test
    void buyProblemTest() {
        inventory.setId(2L);
        when(heroService.getHeroById(anyLong())).thenReturn(hero);
        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(3);
        storeService.buy(1L, "Gucci", 2);
        verify(heroService, times(0)).updateHero(any());
    }


    @Test
    void sellTest() {
        List<String> items = new ArrayList<>();
        items.add("Potion");
        items.add("Wolf paw");
        items.add("Wolf scraps");
        items.add("Wolf pelt");
        items.add("Vest");
        items.add("Pants");
        items.add("Helm");
        items.add("Mask");
        items.add("Boots");
        items.add("Spirit trinket");
        items.add("Dagger");
        items.add("Jewels");
        items.add("Orc necklace");
        items.add("Sword");

        for (int i = 0; i < items.size(); i++) {

            when(heroService.getHeroById(anyLong())).thenReturn(hero);
            doNothing().when(heroService).updateHero(any());
            doNothing().when(inventoryService).removeFirstFromInventory(anyLong(), anyString());
            storeService.sell(1L, items.get(i), 1);
            verify(heroService, times(i + 1)).updateHero(any());
        }
    }

    @Test
    void sellMultipleTest() {
        List<String> items = new ArrayList<>();
        items.add("Potion");
        items.add("Wolf paw");
        items.add("Wolf scraps");
        items.add("Wolf pelt");
        items.add("Vest");
        items.add("Pants");
        items.add("Helm");
        items.add("Mask");
        items.add("Boots");
        items.add("Spirit trinket");
        items.add("Dagger");
        items.add("Jewels");
        items.add("Orc necklace");
        items.add("Sword");

        for (int i = 0; i < items.size(); i++) {

            when(heroService.getHeroById(anyLong())).thenReturn(hero);
            doNothing().when(heroService).updateHero(any());
            doNothing().when(inventoryService).removeFirstFromInventory(anyLong(), anyString());
            storeService.sell(1L, items.get(i), 2);
            verify(heroService, times(i + 1)).updateHero(any());
        }
    }

    @Test
    void sellDefaultTest(){
        when(heroService.getHeroById(anyLong())).thenReturn(hero);
        storeService.sell(1L, "Gucci", 2);

    }

}
