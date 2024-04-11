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
        return switch (item){
            case "potion":
                buyPotion(hero, quantity);
            case "Sword":
                buySword(hero, quantity, inventory);
            default:
                yield "There was a problem processing your purchase. Please try again.";
        };
    }
    public String sell(Long heroId, String item, int quantity) {
        logger.info("Inside sell service method");
        Hero hero = heroService.getHeroById(heroId);
        return switch (item){
            case "potion":
                sellPotion(hero, quantity);
            case "Wolf paw":
                sellWolfPaw(hero, quantity);
            case "Wolf scraps":
                sellWolfScraps(hero, quantity);
            case "Wolf pelt":
                sellWolfPelt(hero, quantity);
            case "Vest":
                sellVest(hero, quantity);
            case "Sword":
                sellSword(hero, quantity);
            case "Pants":
                sellPants(hero, quantity);
            case "Boots":
                sellBoots(hero, quantity);
            case "Dagger":
                sellDagger(hero, quantity);
            case "Helm":
                sellHelm(hero, quantity);
            case "Mask":
                sellMask(hero, quantity);
            case "Orc necklace":
                sellOrcNecklace(hero, quantity);
            case "Jewelry":
                sellJewelry(hero, quantity);
            case "Spirit trinket":
                sellSpiritTrinket(hero, quantity);
            default:
                yield "There was a problem processing your transaction. Please try again.";
        };
    }

    private String buyPotion(Hero hero, int quantity) {
        if (hero.getCoins() - (quantity) < 0) {
            return "Insufficient coins.";
        } else {
            hero.setPotions(hero.getPotions() + quantity);
            hero.setCoins(hero.getCoins() - (quantity));
            heroService.updateHero(hero);
            switch (quantity) {
                case 1: return "You purchased " + quantity + " potion";
                case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12: return "You purchased " + quantity + " potions";
            }
        }
        return "There was a problem processing your purchase. Please try again.";
    }
    private String buySword(Hero hero, int quantity, Inventory inventory) {
        if (hero.getCoins() - (quantity * 5L) < 0) {
            return "Insufficient coins.";
        } else {
            inventoryService.addToFirstEmptySlot(inventory, "Sword");
            hero.setCoins(hero.getCoins() - (quantity * 5L));
            heroService.updateHero(hero);
            switch (quantity) {
                case 1: return "You purchased " + quantity + " sword";
                case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12: return "You purchased " + quantity + " swords";
            }
        }
        return "There was a problem processing your purchase. Please try again.";
    }
    private String sellPotion(Hero hero, int quantity) {
        hero.setPotions(hero.getPotions() - quantity);
        hero.setCoins(hero.getCoins() + (quantity));
        heroService.updateHero(hero);
        return switch (quantity) {
            case 1 -> "You sold " + quantity + " potion";
            case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "You sold " + quantity + " potions";
            default -> "There was a problem processing your transaction. Please try again.";
        };
    }
    private String sellWolfPaw(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Wolf paw");
        }

        hero.setCoins(hero.getCoins() + (quantity * 2L));
        heroService.updateHero(hero);
        return switch (quantity) {
            case 1 -> "You sold " + quantity + " wolf paw";
            case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "You sold " + quantity + " wolf paws";
            default -> "There was a problem processing your transaction. Please try again.";
        };
    }
    private String sellWolfScraps(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Wolf scraps");
        }

        hero.setCoins(hero.getCoins() + (quantity));
        heroService.updateHero(hero);
        return switch (quantity) {
            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "You sold " + quantity + " wolf scraps";
            default -> "There was a problem processing your transaction. Please try again.";
        };
    }
    private String sellWolfPelt(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Wolf pelt");
        }

        hero.setCoins(hero.getCoins() + (quantity * 2L));
        heroService.updateHero(hero);
        return switch (quantity) {
            case 1 -> "You sold " + quantity + " wolf pelt";
            case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "You sold " + quantity + " wolf pelts";
            default -> "There was a problem processing your transaction. Please try again.";
        };
    }
    private String sellVest(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Vest");
        }

        hero.setCoins(hero.getCoins() + (quantity * 3L));
        heroService.updateHero(hero);
        return switch (quantity) {
            case 1 -> "You sold " + quantity + " vest";
            case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "You sold " + quantity + " vests";
            default -> "There was a problem processing your transaction. Please try again.";
        };
    }

    private String sellSword(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Sword");
        }

        hero.setCoins(hero.getCoins() + (quantity * 5L));
        heroService.updateHero(hero);
        return switch (quantity) {
            case 1 -> "You sold " + quantity + " sword";
            case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "You sold " + quantity + " swords";
            default -> "There was a problem processing your transaction. Please try again.";
        };
    }
    private String sellPants(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Pants");
        }

        hero.setCoins(hero.getCoins() + (quantity * 3L));
        heroService.updateHero(hero);
        return switch (quantity) {
            case 1 -> "You sold " + quantity + " pair of pants";
            case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "You sold " + quantity + " pairs of pants";
            default -> "There was a problem processing your transaction. Please try again.";
        };
    }
    private String sellOrcNecklace(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Orc necklace");
        }

        hero.setCoins(hero.getCoins() + (quantity * 4L));
        heroService.updateHero(hero);
        return switch (quantity) {
            case 1 -> "You sold " + quantity + " Orc necklace";
            case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "You sold " + quantity + " Orc necklaces";
            default -> "There was a problem processing your transaction. Please try again.";
        };
    }
    private String sellBoots(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Boots");
        }

        hero.setCoins(hero.getCoins() + (quantity * 3L));
        heroService.updateHero(hero);
        return switch (quantity) {
            case 1 -> "You sold " + quantity + " pair of boots";
            case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "You sold " + quantity + " pairs of boots";
            default -> "There was a problem processing your transaction. Please try again.";
        };
    }
    private String sellHelm(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Helm");
        }

        hero.setCoins(hero.getCoins() + (quantity * 3L));
        heroService.updateHero(hero);
        return switch (quantity) {
            case 1 -> "You sold " + quantity + " helm";
            case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "You sold " + quantity + " helms";
            default -> "There was a problem processing your transaction. Please try again.";
        };
    }
    private String sellSpiritTrinket(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Spirit trinket");
        }

        hero.setCoins(hero.getCoins() + (quantity * 15L));
        heroService.updateHero(hero);
        return switch (quantity) {
            case 1 -> "You sold " + quantity + " spirit trinket";
            case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "You sold " + quantity + " spirit trinkets";
            default -> "There was a problem processing your transaction. Please try again.";
        };
    }
    private String sellDagger(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Dagger");
        }

        hero.setCoins(hero.getCoins() + (quantity * 5L));
        heroService.updateHero(hero);
        return switch (quantity) {
            case 1 -> "You sold " + quantity + " dagger";
            case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "You sold " + quantity + " daggers";
            default -> "There was a problem processing your transaction. Please try again.";
        };
    }
    private String sellMask(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Mask");
        }

        hero.setCoins(hero.getCoins() + (quantity * 3L));
        heroService.updateHero(hero);
        return switch (quantity) {
            case 1 -> "You sold " + quantity + " mask";
            case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "You sold " + quantity + " masks";
            default -> "There was a problem processing your transaction. Please try again.";
        };
    }
    private String sellJewelry(Hero hero, int quantity) {
        for (int i = 0; i < quantity; i++) {
            inventoryService.removeFromInventory(hero.getId(), "Jewelery");
        }

        hero.setCoins(hero.getCoins() + (quantity * 4L));
        heroService.updateHero(hero);
        return switch (quantity) {
            case 1 -> "You sold " + quantity + " jewel";
            case 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> "You sold " + quantity + " jewels";
            default -> "There was a problem processing your transaction. Please try again.";
        };
    }
}
