package com.battlejawn.Controllers;

import com.battlejawn.Config.JsonParser;
import com.battlejawn.Service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventoryControllerTest {
    @Mock
    InventoryService inventoryService;
    @Mock
    JsonParser jsonParser;
    List<String> stringList;
    @InjectMocks
    InventoryController inventoryController;
    @BeforeEach
    void setup(){
        stringList = new ArrayList<>();
    }
    @Test
    void getLootOptionsTest(){
        stringList.add("Hi");
        when(inventoryService.getLootOptions(anyLong())).thenReturn(stringList);
        inventoryController.getLootOptions(1L);
        verify(inventoryService, times(1)).getLootOptions(anyLong());
    }
    @Test
    void getLootOptionsNullTest(){
        when(inventoryService.getLootOptions(anyLong())).thenReturn(null);
        inventoryController.getLootOptions(1L);
        verify(inventoryService, times(1)).getLootOptions(anyLong());
    }
    @Test
    void addToInventoryTest(){
        stringList.add("Hi");
        doNothing().when(inventoryService).updateInventory(anyLong(), any());
        inventoryController.addToInventory(1L, stringList);
        verify(inventoryService, times(1)).updateInventory(anyLong(), any());
    }
    @Test
    void addToInventoryNullTest(){
        inventoryController.addToInventory(1L, null);
        verify(inventoryService, times(0)).updateInventory(anyLong(), any());
    }
    @Test
    void removeFromInventoryTest() {
        stringList.add("Hi");
        when(jsonParser.extractSelectedItems(anyString())).thenReturn(stringList);
        doNothing().when(inventoryService).removeMultipleFromInventory(anyLong(), any());
        inventoryController.removeFromInventory(1L, "String");
        verify(inventoryService, times(1)).removeMultipleFromInventory(anyLong(), any());
    }
    @Test
    void removeFromInventoryNullTest() {
        when(jsonParser.extractSelectedItems(anyString())).thenReturn(null);
        inventoryController.removeFromInventory(1L, "String");
        verify(inventoryService, times(0)).removeFirstFromInventory(anyLong(), any());
    }
    @Test
    void getEmptySlotSizeTest() {
        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(1);
        inventoryController.getEmptySlotSize(1L);
        verify(inventoryService, times(1)).getEmptySlotSize(anyLong());
    }
    @Test
    void getInventoryByIdTest() {
        stringList.add("Hi");
        when(inventoryService.getInventoryById(anyLong())).thenReturn(stringList);
        inventoryController.getInventoryById(1L);
        verify(inventoryService, times(1)).getInventoryById(anyLong());
    }
    @Test
    void getInventoryByIdNullTest() {
        when(inventoryService.getInventoryById(anyLong())).thenReturn(null);
        inventoryController.getInventoryById(1L);
        verify(inventoryService, times(1)).getInventoryById(anyLong());
    }

    @Test
    void getPotionCountByIdTest() {
        when(inventoryService.findPotionCountById(any())).thenReturn(1);
        inventoryController.getPotionCountById(1L);
        verify(inventoryService, times(1)).findPotionCountById(any());
    }
    @Test
    void getPotionCountByIdNullTest() {
        when(inventoryService.findPotionCountById(any())).thenReturn(null);
        inventoryController.getPotionCountById(1L);
        verify(inventoryService, times(1)).findPotionCountById(any());
    }
    @Test
    void usePotionTest() {
        when(jsonParser.extractSlot(any())).thenReturn(1);
        when(inventoryService.usePotion(anyLong(), anyInt())).thenReturn("Hi");
        inventoryController.usePotion(1L, "1");
        verify(inventoryService, times(1)).usePotion(anyLong(), anyInt());
    }
    @Test
    void usePotionNullTest() {
        when(jsonParser.extractSlot(any())).thenReturn(1);
        when(inventoryService.usePotion(anyLong(), anyInt())).thenReturn(null);
        inventoryController.usePotion(1L, "1");
        verify(inventoryService, times(1)).usePotion(anyLong(), anyInt());
    }
}
