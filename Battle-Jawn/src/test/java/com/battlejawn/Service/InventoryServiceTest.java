package com.battlejawn.Service;

import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Enemy.Orc;
import com.battlejawn.Entities.Hero.Healer;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Entities.Inventory;
import com.battlejawn.Repository.InventoryRepository;
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
public class InventoryServiceTest {
    @Mock
    LootService lootService;
    @Mock
    InventoryRepository inventoryRepository;
    @Mock
    EnemyService enemyService;
    @Mock
    HeroService heroService;
    @Mock
    Hero tank;
    @Mock
    Inventory inventory;
    @InjectMocks
    InventoryService inventoryService;
    @BeforeEach
    void setup() {
        tank = new Tank();
        tank.setId(1L);
        inventory = new Inventory();
        tank.setInventory(inventory);
    }
    @Test
    void getInventoryByIdTest() {
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        List<String> jawn = inventoryService.getInventoryById(1L);
        Assertions.assertNotNull(jawn);
    }

    @Test
    void getLootOptionsTest() {
        Enemy enemy = new Orc();
        enemy.setId(1L);
        when(enemyService.getEnemyById(any())).thenReturn(enemy);
        List<String> jawn = inventoryService.getLootOptions(1L);
        Assertions.assertNotNull(jawn);
    }

    @Test
    void updateInventory() {
        List<String> jawn = new ArrayList<>();
        jawn.add("Jawn");
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        inventoryService.updateInventory(1L, jawn);
        verify(heroService, times(1)).getHeroById(anyLong());
    }

    @Test
    void removeFromInventoryTest() {
        List<String> jawn = new ArrayList<>();

        jawn.add("One");
        jawn.add("Two");
        jawn.add("Three");
        jawn.add("Four");
        jawn.add("Five");
        jawn.add("Six");
        jawn.add("Seven");
        jawn.add("Eight");
        jawn.add("Nine");
        jawn.add("Ten");
        jawn.add("Eleven");
        jawn.add("Twelve");

        Inventory inventoryFull = new Inventory();

        inventoryFull.setSlotOne("One");
        inventoryFull.setSlotTwo("Two");
        inventoryFull.setSlotThree("Three");
        inventoryFull.setSlotFour("Four");
        inventoryFull.setSlotFive("Five");
        inventoryFull.setSlotSix("Six");
        inventoryFull.setSlotSeven("Seven");
        inventoryFull.setSlotEight("Eight");
        inventoryFull.setSlotNine("Nine");
        inventoryFull.setSlotTen("Ten");
        inventoryFull.setSlotEleven("Eleven");
        inventoryFull.setSlotTwelve("Twelve");
        System.out.println(inventoryFull);

        Healer healer = new Healer("Name");
        healer.setInventory(inventoryFull);

        when(heroService.getHeroById(anyLong())).thenReturn(healer);
        when(inventoryRepository.save(any())).thenReturn(inventoryFull);

        inventoryService.removeMultipleFromInventory(1L, jawn);

        Assertions.assertEquals(inventory.getSlotOne(), "");
        Assertions.assertEquals(inventory.getSlotTwo(), "");
        Assertions.assertEquals(inventory.getSlotThree(), "");
        Assertions.assertEquals(inventory.getSlotFour(), "");
        Assertions.assertEquals(inventory.getSlotFive(), "");
        Assertions.assertEquals(inventory.getSlotSix(), "");
        Assertions.assertEquals(inventory.getSlotSeven(), "");
        Assertions.assertEquals(inventory.getSlotEight(), "");
        Assertions.assertEquals(inventory.getSlotNine(), "");
        Assertions.assertEquals(inventory.getSlotTen(), "");
        Assertions.assertEquals(inventory.getSlotEleven(), "");
        Assertions.assertEquals(inventory.getSlotTwelve(), "");
    }

    @Test
    void getEmptySlotSizeTest() {
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        System.out.println(tank.getInventory());
        Integer slotSize = inventoryService.getEmptySlotSize(1L);
        Assertions.assertEquals(slotSize, 12);
    }

    @Test
    void addToFirstEmptySlotTest() {
        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.addToFirstEmptySlot(inventory, "Hi");
        verify(inventoryRepository, times(1)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.addToFirstEmptySlot(inventory, "Hi");
        verify(inventoryRepository, times(2)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.addToFirstEmptySlot(inventory, "Hi");
        verify(inventoryRepository, times(3)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.addToFirstEmptySlot(inventory, "Hi");
        verify(inventoryRepository, times(4)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.addToFirstEmptySlot(inventory, "Hi");
        verify(inventoryRepository, times(5)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.addToFirstEmptySlot(inventory, "Hi");
        verify(inventoryRepository, times(6)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.addToFirstEmptySlot(inventory, "Hi");
        verify(inventoryRepository, times(7)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.addToFirstEmptySlot(inventory, "Hi");
        verify(inventoryRepository, times(8)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.addToFirstEmptySlot(inventory, "Hi");
        verify(inventoryRepository, times(9)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.addToFirstEmptySlot(inventory, "Hi");
        verify(inventoryRepository, times(10)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.addToFirstEmptySlot(inventory, "Hi");
        verify(inventoryRepository, times(11)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.addToFirstEmptySlot(inventory, "Hi");
        verify(inventoryRepository, times(12)).save(any());
    }

}
