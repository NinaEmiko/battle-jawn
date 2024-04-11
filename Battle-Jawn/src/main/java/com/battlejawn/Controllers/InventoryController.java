package com.battlejawn.Controllers;

import com.battlejawn.Config.JsonParser;
import com.battlejawn.Service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryController {
    private InventoryService inventoryService;
    private JsonParser jsonParser;
    private final Logger logger = Logger.getLogger(InventoryController.class.getName());
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
    @PutMapping("/add/{id}")
    public ResponseEntity<String> addToInventory(@PathVariable Long id, @RequestBody String selectedItems) {
        List<String> extractedSelectedItems = jsonParser.extractSelectedItems(selectedItems);
        try {
            inventoryService.updateInventory(id, extractedSelectedItems);
            URI location = URI.create("/inventory/");
            return ResponseEntity.created(location).body("Inventory successfully updated.");
        } catch (Exception e) {
            logger.info("Error occurred while updating inventory: " + e);
        }
        return null;
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeFromInventory(@PathVariable Long id, @RequestBody String selectedItems) {
        List<String> extractedSelectedItems = jsonParser.extractSelectedItems(selectedItems);
        try {
            inventoryService.removeFromInventory(id, extractedSelectedItems);
            URI location = URI.create("/inventory/");
            return ResponseEntity.created(location).body("Inventory successfully updated.");
        } catch (Exception e) {
            logger.info("Error occurred while removing items from inventory: " + e);
        }
        return null;
    }
    @GetMapping("/slots/{id}")
    public ResponseEntity<Integer> getEmptySlotSize(@PathVariable Long id){
        try {
            Integer emptySlotSize = inventoryService.getEmptySlotSize(id);
            URI location = URI.create("/inventory/");
            return ResponseEntity.created(location).body(emptySlotSize);
        } catch (Exception e) {
            logger.info("Error occurred while retrieving inventory size: " + e);
        }
        return null;
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<String>> getInventoryById(@PathVariable Long id){
        try {
            List<String> inventory = inventoryService.getInventoryById(id);
            URI location = URI.create("/inventory/");
            return ResponseEntity.created(location).body(inventory);
        } catch (Exception e) {
            logger.info("Error occurred while retrieving inventory: " + e);
        }
        return null;
    }
}
