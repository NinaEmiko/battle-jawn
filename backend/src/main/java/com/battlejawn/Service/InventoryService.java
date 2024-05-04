package com.battlejawn.Service;

import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Inventory;
import com.battlejawn.Repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class InventoryService {
    private LootService lootService;
    private InventoryRepository inventoryRepository;
    private EnemyService enemyService;
    private HeroService heroService;
    private final Logger logger = Logger.getLogger(InventoryService.class.getName());

    public String usePotion(Long id, int slot) {
        String msg = "";
        Hero hero = heroService.getHeroById(id);
        if (hero.getHealth() == hero.getMaxHealth()) {
            msg = hero.getName() + " is at max health.";
        } else if (hero.getMaxHealth() - hero.getHealth() < 30) {
            removeFromInventory(id, slot);
            msg = hero.getName() + " used a potion.";
            hero.setHealth(hero.getMaxHealth());
        } else {
            removeFromInventory(id, slot);
            msg = hero.getName() + " used a potion.";
            hero.setHealth(hero.getHealth() + 30);
        }
        heroService.updateHero(hero);
        return msg;
    }

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
        return lootService.getLoot(enemyId);
    }
    public void updateInventory(Long id, List<String> selectedLoot){
        Hero hero = heroService.getHeroById(id);
        Inventory inventory = hero.getInventory();

        for (String loot : selectedLoot) {
            addToFirstEmptySlot(inventory, loot);
        }
    }

    public void removeMultipleFromInventory(Long id, List<String> items){
        for (String item : items) {
            removeFirstFromInventory(id, item);
        }
    }

    public void removeFirstFromInventory(Long id, String item){
        Hero hero = heroService.getHeroById(id);
        Inventory inventory = hero.getInventory();

        if(inventory.getSlotOne().equals(item)) {
            inventory.setSlotOne("");
        } else if(inventory.getSlotTwo().equals(item)) {
            inventory.setSlotTwo("");
        } else if(inventory.getSlotThree().equals(item)){
            inventory.setSlotThree("");
        } else if(inventory.getSlotFour().equals(item)){
            inventory.setSlotFour("");
        } else if(inventory.getSlotFive().equals(item)){
            inventory.setSlotFive("");
        } else if(inventory.getSlotSix().equals(item)){
            inventory.setSlotSix("");
        } else if(inventory.getSlotSeven().equals(item)){
            inventory.setSlotSeven("");
        } else if(inventory.getSlotEight().equals(item)){
            inventory.setSlotEight("");
        } else if(inventory.getSlotNine().equals(item)){
            inventory.setSlotNine("");
        } else if(inventory.getSlotTen().equals(item)){
            inventory.setSlotTen("");
        } else if(inventory.getSlotEleven().equals(item)){
            inventory.setSlotEleven("");
        } else if(inventory.getSlotTwelve().equals(item)){
            inventory.setSlotTwelve("");
        }
        inventoryRepository.save(inventory);
    }

    public void removeFromInventory(Long id, int slot){
        Hero hero = heroService.getHeroById(id);
        Inventory inventory = hero.getInventory();

        switch (slot) {
            case 1:
                inventory.setSlotOne("");
                break;
            case 2:
                inventory.setSlotTwo("");
                break;
            case 3:
                inventory.setSlotThree("");
                break;
            case 4:
                inventory.setSlotFour("");
                break;
            case 5:
                inventory.setSlotFive("");
                break;
            case 6:
                inventory.setSlotSix("");
                break;
            case 7:
                inventory.setSlotSeven("");
                break;
            case 8:
                inventory.setSlotEight("");
                break;
            case 9:
                inventory.setSlotNine("");
                break;
            case 10:
                inventory.setSlotTen("");
                break;
            case 11:
                inventory.setSlotEleven("");
                break;
            case 12:
                inventory.setSlotTwelve("");
                break;
        }
        inventoryRepository.save(inventory);
    }
    public void addToFirstEmptySlot(Inventory inventory, String item){
        logger.info("Inside addToFirstEmptySlot service method");

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
            if (value){
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

    public Integer findPotionCountById(Long id) {
        Hero hero = heroService.getHeroById(id);
        Inventory inventory = hero.getInventory();
        return findPotionCount(inventory);
    }

    public int findPotionCount(Inventory inventory) {
        int potionCount = 0;
        if (Objects.equals(inventory.getSlotOne(), "Potion")){
            potionCount++;
        }
        if (Objects.equals(inventory.getSlotTwo(), "Potion")){
            potionCount++;
        }
        if (Objects.equals(inventory.getSlotThree(), "Potion")){
            potionCount++;
        }
        if (Objects.equals(inventory.getSlotFour(), "Potion")){
            potionCount++;
        }
        if (Objects.equals(inventory.getSlotFive(), "Potion")){
            potionCount++;
        }
        if (Objects.equals(inventory.getSlotSix(), "Potion")){
            potionCount++;
        }
        if (Objects.equals(inventory.getSlotSeven(), "Potion")){
            potionCount++;
        }
        if (Objects.equals(inventory.getSlotEight(), "Potion")){
            potionCount++;
        }
        if (Objects.equals(inventory.getSlotNine(), "Potion")){
            potionCount++;
        }
        if (Objects.equals(inventory.getSlotTen(), "Potion")){
            potionCount++;
        }
        if (Objects.equals(inventory.getSlotEleven(), "Potion")){
            potionCount++;
        }
        if (Objects.equals(inventory.getSlotTwelve(), "Potion")){
            potionCount++;
        }
        return potionCount;
    }
}
