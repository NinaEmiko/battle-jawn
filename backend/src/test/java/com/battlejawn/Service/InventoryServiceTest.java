package com.battlejawn.Service;

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
        List<String> list = new ArrayList<>();
        when(lootService.getLoot(anyLong())).thenReturn(list);
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
    void removeMulitpleFromInventoryTest() {
        List<String> jawn = new ArrayList<>();

        jawn.add("Potion");
        jawn.add("Potion");
        jawn.add("Potion");
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

        Assertions.assertEquals(inventoryFull.getSlotOne(), "");
        Assertions.assertEquals(inventoryFull.getSlotTwo(), "");
        Assertions.assertEquals(inventoryFull.getSlotThree(), "");
        Assertions.assertEquals(inventoryFull.getSlotFour(), "");
        Assertions.assertEquals(inventoryFull.getSlotFive(), "");
        Assertions.assertEquals(inventoryFull.getSlotSix(), "");
        Assertions.assertEquals(inventoryFull.getSlotSeven(), "");
        Assertions.assertEquals(inventoryFull.getSlotEight(), "");
        Assertions.assertEquals(inventoryFull.getSlotNine(), "");
        Assertions.assertEquals(inventoryFull.getSlotTen(), "");
        Assertions.assertEquals(inventoryFull.getSlotEleven(), "");
        Assertions.assertEquals(inventoryFull.getSlotTwelve(), "");
    }

    @Test
    void getEmptySlotSizeTest() {
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        System.out.println(tank.getInventory());
        Integer slotSize = inventoryService.getEmptySlotSize(1L);
        Assertions.assertEquals(slotSize, 9);
    }

    @Test
    void addToFirstEmptySlotTest() {
        inventory.setSlotOne("");
        inventory.setSlotTwo("");
        inventory.setSlotThree("");
        inventory.setSlotFour("");
        inventory.setSlotFive("");
        inventory.setSlotSix("");
        inventory.setSlotSeven("");
        inventory.setSlotEight("");
        inventory.setSlotNine("");
        inventory.setSlotTen("");
        inventory.setSlotEleven("");
        inventory.setSlotTwelve("");


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

//    @Test
//    void findPotionCountById() {
//        Hero hero = new Healer("Name");
//        Inventory inventory = new Inventory();
//        inventory.setSlotOne("Potion");
//        inventory.setSlotTwo("Potion");
//        inventory.setSlotThree("Potion");
//        inventory.setSlotFour("Potion");
//        inventory.setSlotFive("Potion");
//        inventory.setSlotSix("Potion");
//        inventory.setSlotSeven("Potion");
//        inventory.setSlotEight("Potion");
//        inventory.setSlotNine("Potion");
//        inventory.setSlotTen("Potion");
//        inventory.setSlotEleven("Potion");
//        inventory.setSlotTwelve("Potion");
//        hero.setInventory(inventory);
//        when(heroService.getHeroById(anyLong())).thenReturn(hero);
//        inventoryService.findPotionCountById(1L);
//        verify(heroService, times(1)).getHeroById(anyLong());
//    }

    @Test
    void usePotionMaxHealthTest() {
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        inventoryService.usePotion(1L ,1);
        verify(heroService, times(0)).updateHero(any());
    }
    @Test
    void usePotionAlmostFullTest() {
        tank.setHealth(110);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        doNothing().when(heroService).updateHero(any());
        inventoryService.usePotion(1L ,1);
        verify(heroService, times(1)).updateHero(any());
    }

    @Test
    void usePotionTest() {
        tank.setHealth(1);
        tank.setMaxHealth(100);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        doNothing().when(heroService).updateHero(any());
        inventoryService.usePotion(1L ,1);
        verify(heroService, times(1)).updateHero(any());
    }

    @Test
    void useWaterTest(){
        tank.setResource(1);
        tank.setMaxResource(3);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        doNothing().when(heroService).updateHero(any());
        inventoryService.useWater(1L ,1);
        verify(heroService, times(1)).updateHero(any());
    }
    @Test
    void useWaterMaxResourceTankTest(){
        tank.setResource(3);
        tank.setMaxResource(3);
        tank.setRole("Tank");
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        inventoryService.useWater(1L ,1);
        verify(heroService, times(0)).updateHero(any());
    }
    @Test
    void useWaterMaxResourceHealerTest(){
        tank.setResource(3);
        tank.setMaxResource(3);
        tank.setRole("Healer");
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        inventoryService.useWater(1L ,1);
        verify(heroService, times(0)).updateHero(any());
    }
    @Test
    void useWaterMaxResourceCasterTest(){
        tank.setResource(3);
        tank.setMaxResource(3);
        tank.setRole("Caster");
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        inventoryService.useWater(1L ,1);
        verify(heroService, times(0)).updateHero(any());
    }
    @Test
    void useWaterMaxResourceDPSTest(){
        tank.setResource(3);
        tank.setMaxResource(3);
        tank.setRole("DPS");
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        inventoryService.useWater(1L ,1);
        verify(heroService, times(0)).updateHero(any());
    }
    @Test
    void removeFromInventoryTest(){
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        Inventory inventory1 = new Inventory();
        tank.setInventory(inventory1);

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.removeFromInventory(1L, 2);
        verify(inventoryRepository, times(1)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.removeFromInventory(1L, 3);
        verify(inventoryRepository, times(2)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.removeFromInventory(1L, 4);
        verify(inventoryRepository, times(3)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.removeFromInventory(1L, 5);
        verify(inventoryRepository, times(4)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.removeFromInventory(1L, 6);
        verify(inventoryRepository, times(5)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.removeFromInventory(1L, 7);
        verify(inventoryRepository, times(6)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.removeFromInventory(1L, 8);
        verify(inventoryRepository, times(7)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.removeFromInventory(1L, 9);
        verify(inventoryRepository, times(8)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.removeFromInventory(1L, 10);
        verify(inventoryRepository, times(9)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.removeFromInventory(1L, 11);
        verify(inventoryRepository, times(10)).save(any());

        when(inventoryRepository.save(any())).thenReturn(inventory);
        inventoryService.removeFromInventory(1L, 12);
        verify(inventoryRepository, times(11)).save(any());

    }
}
