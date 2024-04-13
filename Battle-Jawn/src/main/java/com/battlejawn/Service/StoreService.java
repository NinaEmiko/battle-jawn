package com.battlejawn.Service;

import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Inventory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class StoreService {
    private final HeroService heroService;
    private final InventoryService inventoryService;
    private final Logger logger = Logger.getLogger(StoreService.class.getName());

    public String buy(Long heroId, String item, int quantity) {
        logger.info("Inside buy service method");
        Hero hero = heroService.getHeroById(heroId);
        Inventory inventory = hero.getInventory();
        if (inventoryService.getEmptySlotSize(heroId) < quantity) {
            return "You do not have room in your inventory for this purchase.";
        }
        return switch (item){
            case "potion" -> buyPotion(hero, quantity, inventory);
            case "Sword" -> buySword(hero, quantity, inventory);
            default -> "There was a problem processing your purchase. Please try again.";
        };
    }
    public String sell(Long heroId, String item, int quantity) {
        logger.info("Inside sell service method");
        Hero hero = heroService.getHeroById(heroId);
        return switch (item){
            case "potion" -> sellPotion(hero, quantity);
            case "Wolf paw" -> sellWolfPaw(hero, quantity);
            case "Wolf scraps" -> sellWolfScraps(hero, quantity);
            case "Wolf pelt" -> sellWolfPelt(hero, quantity);
            case "Vest" -> sellVest(hero, quantity);
            case "Sword" -> sellSword(hero, quantity);
            case "Pants" -> sellPants(hero, quantity);
            case "Boots" -> sellBoots(hero, quantity);
            case "Dagger" -> sellDagger(hero, quantity);
            case "Helm" -> sellHelm(hero, quantity);
            case "Mask" -> sellMask(hero, quantity);
            case "Orc Necklace" -> sellOrcNecklace(hero, quantity);
            case "Jewelery" -> sellJewelery(hero, quantity);
            case "Spirit trinket" -> sellSpiritTrinket(hero, quantity);
            default -> "There was a problem processing your transaction. Please try again.";
        };
    }
    private String buyPotion(Hero hero, int quantity, Inventory inventory) {
        logger.info("Inside buyPotion service method");

        String msg = "";
        if (hero.getCoins() - (quantity) < 0) {
            msg = "Insufficient coins.";
        } else {
            inventoryService.addToFirstEmptySlot(inventory, "potion");
            hero.setCoins(hero.getCoins() - quantity);
            heroService.updateHero(hero);
            msg = produceGenericMessage("purchased", quantity, "potion");
        }
        return msg;
    }
    private String buySword(Hero hero, int quantity, Inventory inventory) {
        if (hero.getCoins() - (quantity * 5L) < 0) {
            return "Insufficient coins.";
        } else {
            inventoryService.addToFirstEmptySlot(inventory, "Sword");
            hero.setCoins(hero.getCoins() - (quantity * 5L));
            heroService.updateHero(hero);
            return produceGenericMessage("purchased", quantity, "sword");
        }
    }
    private String sellPotion(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "potion");
        }
        hero.setCoins(hero.getCoins() + (quantity));
        heroService.updateHero(hero);
        return produceGenericMessage("sold", quantity, "potion");
    }
    private String sellWolfPaw(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Wolf paw");
        }
        hero.setCoins(hero.getCoins() + (quantity * 2L));
        heroService.updateHero(hero);
        return produceGenericMessage("sold", quantity, "wolf paw");
    }
    private String sellWolfScraps(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Wolf scraps");
        }
        hero.setCoins(hero.getCoins() + (quantity));
        heroService.updateHero(hero);
        return produceGenericSetMessage("sold", quantity, "wold scraps");
    }
    private String sellWolfPelt(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Wolf pelt");
        }
        hero.setCoins(hero.getCoins() + (quantity * 2L));
        heroService.updateHero(hero);
        return produceGenericMessage("sold", quantity, "wolf pelt");
    }
    private String sellVest(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Vest");
        }
        hero.setCoins(hero.getCoins() + (quantity * 3L));
        heroService.updateHero(hero);
        return produceGenericMessage("sold", quantity, "vest");
    }
    private String sellSword(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Sword");
        }
        hero.setCoins(hero.getCoins() + (quantity * 5L));
        heroService.updateHero(hero);
        return produceGenericMessage("sold", quantity, "sword");
    }
    private String sellPants(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Pants");
        }
        hero.setCoins(hero.getCoins() + (quantity * 3L));
        heroService.updateHero(hero);
        return produceGenericPairMessage("sold", quantity, "pants");
    }
    private String sellOrcNecklace(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Orc Necklace");
        }
        hero.setCoins(hero.getCoins() + (quantity * 4L));
        heroService.updateHero(hero);
        return produceGenericMessage("sold", quantity, "orc necklace");
    }
    private String sellBoots(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Boots");
        }
        hero.setCoins(hero.getCoins() + (quantity * 3L));
        heroService.updateHero(hero);
        return produceGenericPairMessage("sold", quantity, "boots");
    }
    private String sellHelm(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Helm");
        }
        hero.setCoins(hero.getCoins() + (quantity * 3L));
        heroService.updateHero(hero);
        return produceGenericMessage("sold", quantity, "helm");
    }
    private String sellSpiritTrinket(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Spirit trinket");
        }
        hero.setCoins(hero.getCoins() + (quantity * 15L));
        heroService.updateHero(hero);
        return produceGenericMessage("sold", quantity, "spirit trinket");
    }
    private String sellDagger(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Dagger");
        }
        hero.setCoins(hero.getCoins() + (quantity * 5L));
        heroService.updateHero(hero);
        return produceGenericMessage("sold", quantity, "dagger");
    }
    private String sellMask(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Mask");
        }
        hero.setCoins(hero.getCoins() + (quantity * 3L));
        heroService.updateHero(hero);
        return produceGenericMessage("sold", quantity, "mask");
    }
    private String sellJewelery(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Jewelery");
        }
        hero.setCoins(hero.getCoins() + (quantity * 4L));
        heroService.updateHero(hero);
        return produceGenericSetMessage("sold", quantity, "jewelery");
    }
    private String produceGenericMessage(String transaction, int quantity, String item) {
        if (quantity == 1) {
            return "You " + transaction + " " + quantity + " " + item + ".";
        }
        return "You " + transaction + " " + quantity + " " + item + "s.";
    }
    private String produceGenericSetMessage(String transaction, int quantity, String item) {
        if (quantity == 1) {
            return "You " + transaction + " " + quantity + " set of " + item + ".";
        }
        return "You " + transaction + " " + quantity + " sets of " + item + "s.";
    }
    private String produceGenericPairMessage(String transaction, int quantity, String item) {
        if (quantity == 1) {
            return "You " + transaction + " " + quantity + " pair of " + item + ".";
        }
        return "You " + transaction + " " + quantity + " pairs of " + item + "s.";
    }
}