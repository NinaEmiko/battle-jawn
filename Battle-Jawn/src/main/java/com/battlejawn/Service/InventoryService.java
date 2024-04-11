package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Inventory;
import com.battlejawn.Repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryService {
    private LootService lootService;
    private InventoryRepository inventoryRepository;
    private EnemyService enemyService;
    private HeroService heroService;
    public List<String> getInventoryById(Long id) {
        Hero hero = heroService.getHeroById(id);
        Inventory inventory = hero.getInventory();
        List<String> inventoryList = new ArrayList<>();

        inventoryList.add(inventory.getSlotOne());
        inventoryList.add(inventory.getSlotTwo());
        inventoryList.add(inventory.getSlotThree());
        inventoryList.add(inventory.getSlotFour());
        inventoryList.add(inventory.getSlotFive());
        inventoryList.add(inventory.getSlotSix());
        inventoryList.add(inventory.getSlotSeven());
        inventoryList.add(inventory.getSlotEight());
        inventoryList.add(inventory.getSlotNine());
        inventoryList.add(inventory.getSlotTen());
        inventoryList.add(inventory.getSlotEleven());
        inventoryList.add(inventory.getSlotTwelve());

        return inventoryList;
    }
    public List<String> getLootOptions(Long enemyId){
        Enemy enemy = enemyService.getEnemyById(enemyId);
        return lootService.getLoot(enemy);
    }
    public void updateInventory(Long id, List<String> selectedLoot){
        Hero hero = heroService.getHeroById(id);
        Inventory inventory = hero.getInventory();

        for (String loot : selectedLoot) {
            addToFirstEmptySlot(inventory, loot);
        }
    }
    public void removeFromInventory(Long id, List<String> selectedItems){
        Hero hero = heroService.getHeroById(id);
        Inventory inventory = hero.getInventory();

        for(String item : selectedItems) {
            if(inventory.getSlotOne().equals(item)){
                inventory.setSlotOne(null);
            } else if(inventory.getSlotTwo().equals(item)){
                inventory.setSlotTwo(null);
            } else if(inventory.getSlotThree().equals(item)){
                inventory.setSlotThree(null);
            } else if(inventory.getSlotFour().equals(item)){
                inventory.setSlotFour(null);
            } else if(inventory.getSlotFive().equals(item)){
                inventory.setSlotFive(null);
            } else if(inventory.getSlotSix().equals(item)){
                inventory.setSlotSix(null);
            } else if(inventory.getSlotSeven().equals(item)){
                inventory.setSlotSeven(null);
            } else if(inventory.getSlotEight().equals(item)){
                inventory.setSlotEight(null);
            } else if(inventory.getSlotNine().equals(item)){
                inventory.setSlotNine(null);
            } else if(inventory.getSlotTen().equals(item)){
                inventory.setSlotTen(null);
            } else if(inventory.getSlotEleven().equals(item)){
                inventory.setSlotEleven(null);
            } else if(inventory.getSlotTwelve().equals(item)){
                inventory.setSlotTwelve(null);
            }
            inventoryRepository.save(inventory);
        }
    }

    private void addToFirstEmptySlot(Inventory inventory, String item){
        if (inventory.getSlotOne().isEmpty()) {
            inventory.setSlotOne(item);
        } else if (inventory.getSlotTwo().isEmpty()){
            inventory.setSlotTwo(item);
        } else if (inventory.getSlotThree().isEmpty()){
            inventory.setSlotThree(item);
        } else if (inventory.getSlotFour().isEmpty()){
            inventory.setSlotFour(item);
        } else if (inventory.getSlotFive().isEmpty()){
            inventory.setSlotFive(item);
        } else if (inventory.getSlotSix().isEmpty()){
            inventory.setSlotSix(item);
        } else if (inventory.getSlotSeven().isEmpty()){
            inventory.setSlotSeven(item);
        } else if (inventory.getSlotEight().isEmpty()){
            inventory.setSlotEight(item);
        } else if (inventory.getSlotNine().isEmpty()){
            inventory.setSlotNine(item);
        } else if (inventory.getSlotTen().isEmpty()){
            inventory.setSlotTen(item);
        } else if (inventory.getSlotEleven().isEmpty()){
            inventory.setSlotEleven(item);
        } else {
            inventory.setSlotTwelve(item);
        }
        inventoryRepository.save(inventory);
    }

    public Integer getEmptySlotSize(Long id) {
        Hero hero = heroService.getHeroById(id);
        Inventory inventory = hero.getInventory();
        Integer inventorySize = 0;
        List<Boolean> emptySlots = getEmptySlots(inventory);

        for (Boolean value : emptySlots) {
            if (!value){
                inventorySize++;
            }
        }

        return inventorySize;
    }

    private List<Boolean> getEmptySlots(Inventory inventory) {
        List<Boolean> emptySlots = new ArrayList<>();
        emptySlots.add(inventory.getSlotOne().isEmpty());
        emptySlots.add(inventory.getSlotTwo().isEmpty());
        emptySlots.add(inventory.getSlotThree().isEmpty());
        emptySlots.add(inventory.getSlotFour().isEmpty());
        emptySlots.add(inventory.getSlotFive().isEmpty());
        emptySlots.add(inventory.getSlotSix().isEmpty());
        emptySlots.add(inventory.getSlotSeven().isEmpty());
        emptySlots.add(inventory.getSlotEight().isEmpty());
        emptySlots.add(inventory.getSlotNine().isEmpty());
        emptySlots.add(inventory.getSlotTen().isEmpty());
        emptySlots.add(inventory.getSlotEleven().isEmpty());
        emptySlots.add(inventory.getSlotTwelve().isEmpty());
        return emptySlots;
    }
}
