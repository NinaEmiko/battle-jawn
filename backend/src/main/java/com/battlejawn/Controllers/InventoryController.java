package com.battlejawn.Controllers;

import com.battlejawn.Config.JsonParser;
import com.battlejawn.Service.InventoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@RestController
@Validated
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryController {
    private InventoryService inventoryService;
    private JsonParser jsonParser;
    private final Logger logger = Logger.getLogger(InventoryController.class.getName());

    @PostMapping("/potion/{id}")
    public ResponseEntity<String> usePotion(@PathVariable Long id, @Valid @RequestBody String slot){
        int extractedSlot = jsonParser.extractSlot(slot);
        String response = inventoryService.usePotion(id, extractedSlot);
        if (response != null) {
            URI location = URI.create("/potion/");
            return ResponseEntity.created(location).body(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/loot-options/{id}")
    public ResponseEntity<List<String>> getLootOptions(@PathVariable Long id) {
        List<String> loot = inventoryService.getLootOptions(id);
        if (loot != null) {
            URI location = URI.create("/loot/");
            return ResponseEntity.created(location).body(loot);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add/{id}")
    public ResponseEntity<String> addToInventory(@PathVariable Long id,@Valid @RequestBody List<String> selectedItems) {

        if (selectedItems != null){
            inventoryService.updateInventory(id, selectedItems);
            URI location = URI.create("/inventory/");
            return ResponseEntity.created(location).body("Inventory successfully updated.");
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeFromInventory(@PathVariable Long id, @Valid @RequestBody String selectedItems) {
        List<String> extractedSelectedItems = jsonParser.extractSelectedItems(selectedItems);
        if (extractedSelectedItems != null) {
            inventoryService.removeMultipleFromInventory(id, extractedSelectedItems);
            URI location = URI.create("/inventory/");
            return ResponseEntity.created(location).body("Inventory successfully updated.");
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/slots/{id}")
    public ResponseEntity<Integer> getEmptySlotSize(@PathVariable Long id){
        Integer emptySlotSize = inventoryService.getEmptySlotSize(id);
        URI location = URI.create("/inventory/");
        return ResponseEntity.created(location).body(emptySlotSize);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<String>> getInventoryById(@PathVariable Long id){
        List<String> inventory = inventoryService.getInventoryById(id);

        if (inventory != null) {
            return new ResponseEntity<>(inventory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/potions/{id}")
    public ResponseEntity<Integer> getPotionCountById(@PathVariable Long id){
        Integer potionCount = inventoryService.findPotionCountById(id);

        if (potionCount != null) {
            return new ResponseEntity<>(potionCount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
