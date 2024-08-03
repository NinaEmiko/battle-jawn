package com.battlejawn.Service;

import com.battlejawn.Entities.AdditionalInventory;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Inventory;
import com.battlejawn.Entities.TalentTree.DPSTree;
import com.battlejawn.Repository.AdditionalInventoryRepository;
import com.battlejawn.Repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class InventoryService {
    private LootService lootService;
    private InventoryRepository inventoryRepository;
    private HeroService heroService;
    private AdditionalInventoryRepository additionalInventoryRepository;
    private final Logger logger = Logger.getLogger(InventoryService.class.getName());

    public String usePotion(Long id, int slot) {
        Hero hero = heroService.getHeroById(id);
        if (hero.getHealth() == hero.getMaxHealth()) {
            return hero.getName() + " is at max health.";
        } else if (hero.getMaxHealth() - hero.getHealth() < 30) {
            removeFromInventory(id, slot);
            hero.setHealth(hero.getMaxHealth());
            heroService.updateHero(hero);
            return hero.getName() + " used a potion.";
        } else {
            removeFromInventory(id, slot);
            hero.setHealth(hero.getHealth() + 30);
            heroService.updateHero(hero);
            return hero.getName() + " used a potion.";
        }
    }

    public String useWater(Long id, int slot) {
        Hero hero = heroService.getHeroById(id);
        if (hero.getResource() == hero.getMaxResource()) {
            switch (hero.getRole()){
                case "Tank":
                    return hero.getName() + " is at max power.";
                case "Healer":
                    return hero.getName() + " is at max spirit.";
                case "Caster":
                    return hero.getName() + " is at max magic.";
                default:
                    return hero.getName() + " is at max energy.";
            }
        } else {
            removeFromInventory(id, slot);
            hero.setResource(hero.getMaxResource());
            heroService.updateHero(hero);
            return hero.getName() + " drank water.";
        }
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

        if (Objects.equals(hero.getRole(), "DPS")){
            DPSTree dpsTree = (DPSTree) hero.getTalentTree();
            if (dpsTree.isOrganizedMess()){

                AdditionalInventory additionalInventory = getAdditionalInventory(hero.getId());
                inventoryList.add(additionalInventory.getSlotOne());
                inventoryList.add(additionalInventory.getSlotTwo());
                inventoryList.add(additionalInventory.getSlotThree());
                inventoryList.add(additionalInventory.getSlotFour());

            }
        }

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
        } else if (Objects.equals(hero.getRole(), "DPS")) {
            DPSTree dpsTree = (DPSTree) hero.getTalentTree();
            if (dpsTree.isOrganizedMess()) {
                AdditionalInventory additionalInventory = getAdditionalInventory(hero.getId());

                if (additionalInventory.getSlotOne().equals(item)) {
                    additionalInventory.setSlotOne("");
                } else if (additionalInventory.getSlotTwo().equals(item)) {
                    additionalInventory.setSlotTwo("");
                } else if (additionalInventory.getSlotThree().equals(item)) {
                    additionalInventory.setSlotThree("");
                } else if (additionalInventory.getSlotFour().equals(item)) {
                    additionalInventory.setSlotFour("");
                }
                additionalInventoryRepository.save(additionalInventory);
            }
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
            case 13:
                removeFromAdditionalInventory(13, hero.getId());
                break;
            case 14:
                removeFromAdditionalInventory(14, hero.getId());
                break;
            case 15:
                removeFromAdditionalInventory(15, hero.getId());
                break;
            case 16:
                removeFromAdditionalInventory(16, hero.getId());
                break;
        }
        inventoryRepository.save(inventory);
    }

    public void removeFromAdditionalInventory(int slot, Long heroId){
        AdditionalInventory additionalInventory = getAdditionalInventory(heroId);
        switch (slot) {
            case 13:
                additionalInventory.setSlotOne("");
                break;
            case 14:
                additionalInventory.setSlotTwo("");
                break;
            case 15:
                additionalInventory.setSlotThree("");
                break;
            case 16:
                additionalInventory.setSlotFour("");
                break;
        }
        additionalInventoryRepository.save(additionalInventory);
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
        } else if (inventory.getSlotTwelve().isEmpty()){
            inventory.setSlotTwelve(item);
        } else if (Objects.equals(inventory.getHero().getRole(), "DPS")){
            DPSTree dpsTree = (DPSTree) inventory.getHero().getTalentTree();
            if (dpsTree.isOrganizedMess()){
                addToFirstEmptyAdditionalSlot(item, inventory.getHero().getId());
            }
        }
        inventoryRepository.save(inventory);
    }

    public void addToFirstEmptyAdditionalSlot(String item, Long heroId) {
        AdditionalInventory additionalInventory = getAdditionalInventory(heroId);

        if (additionalInventory.getSlotOne().isEmpty()) {
            additionalInventory.setSlotOne(item);
        } else if (additionalInventory.getSlotTwo().isEmpty()){
            additionalInventory.setSlotTwo(item);
        } else if (additionalInventory.getSlotThree().isEmpty()){
            additionalInventory.setSlotThree(item);
        } else if (additionalInventory.getSlotFour().isEmpty()) {
            additionalInventory.setSlotFour(item);
        }

        additionalInventoryRepository.save(additionalInventory);
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
        if (Objects.equals(hero.getRole(), "DPS")){
            DPSTree dpsTree = (DPSTree) hero.getTalentTree();
            if (dpsTree.isOrganizedMess()){
                AdditionalInventory additionalInventory = getAdditionalInventory(hero.getId());
                inventorySize += (Objects.equals(additionalInventory.getSlotOne(), "")) ? 1 : 0;
                inventorySize += (Objects.equals(additionalInventory.getSlotTwo(), "")) ? 1 : 0;
                inventorySize += (Objects.equals(additionalInventory.getSlotThree(), "")) ? 1 : 0;
                inventorySize += (Objects.equals(additionalInventory.getSlotFour(), "")) ? 1 : 0;
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
        int itemCount = 0;
        itemCount += findItemCount(inventory, "Potion");

        if (Objects.equals(hero.getRole(), "DPS")){
            DPSTree dpsTree = (DPSTree) hero.getTalentTree();
            if (dpsTree.isOrganizedMess()){
                AdditionalInventory additionalInventory = getAdditionalInventory(hero.getId());
                itemCount += (Objects.equals(additionalInventory.getSlotOne(), "Potion")) ? 1 : 0;
                itemCount += (Objects.equals(additionalInventory.getSlotTwo(), "Potion")) ? 1 : 0;
                itemCount += (Objects.equals(additionalInventory.getSlotThree(), "Potion")) ? 1 : 0;
                itemCount += (Objects.equals(additionalInventory.getSlotFour(), "Potion")) ? 1 : 0;
            }
        }

        return itemCount;
    }

    public int findItemCount(Inventory inventory, String item) {
        int count = 0;
        if (Objects.equals(inventory.getSlotOne(), item)){
            count++;
        }
        if (Objects.equals(inventory.getSlotTwo(), item)){
            count++;
        }
        if (Objects.equals(inventory.getSlotThree(), item)){
            count++;
        }
        if (Objects.equals(inventory.getSlotFour(), item)){
            count++;
        }
        if (Objects.equals(inventory.getSlotFive(), item)){
            count++;
        }
        if (Objects.equals(inventory.getSlotSix(), item)){
            count++;
        }
        if (Objects.equals(inventory.getSlotSeven(), item)){
            count++;
        }
        if (Objects.equals(inventory.getSlotEight(), item)){
            count++;
        }
        if (Objects.equals(inventory.getSlotNine(), item)){
            count++;
        }
        if (Objects.equals(inventory.getSlotTen(), item)){
            count++;
        }
        if (Objects.equals(inventory.getSlotEleven(), item)){
            count++;
        }
        if (Objects.equals(inventory.getSlotTwelve(), item)){
            count++;
        }
        if (Objects.equals(inventory.getHero().getRole(), "DPS")){
            DPSTree dpsTree = (DPSTree) inventory.getHero().getTalentTree();
            if (dpsTree.isOrganizedMess()){
                AdditionalInventory additionalInventory = getAdditionalInventory(inventory.getHero().getId());
                count += (Objects.equals(additionalInventory.getSlotOne(), item)) ? 1 : 0;
                count += (Objects.equals(additionalInventory.getSlotTwo(), item)) ? 1 : 0;
                count += (Objects.equals(additionalInventory.getSlotThree(), item)) ? 1 : 0;
                count += (Objects.equals(additionalInventory.getSlotFour(), item)) ? 1 : 0;
            }
        }
        return count;
    }

    public AdditionalInventory getAdditionalInventory(Long heroId) {
        Optional<AdditionalInventory> additionalInventoryOptional = Optional.ofNullable(additionalInventoryRepository.findAdditionalInventoryByHeroId(heroId));
        return additionalInventoryOptional.orElse(null);
    }
}
