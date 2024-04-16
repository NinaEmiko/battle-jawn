package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Randomizer.Randomizer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LootService {
    private Randomizer randomizer;
    private EnemyService enemyService;

    public List<String> getLoot(Long id) {
        Enemy enemy = enemyService.getEnemyById(id);
        return switch (enemy.getName()) {
            case "Wolf" -> determineWolfLoot();
            case "Spirit" -> determineSpiritLoot();
            case "Orc" -> determineOrcLoot(enemy.getPotions());
            case "Thief" -> determineThiefLoot(enemy.getPotions());
            default -> null;
        };
    }
    private List<String> determineWolfLoot() {
        List<String> wolfLoot = new ArrayList<>();
        int wolfPawChance = randomizer.getRandomInt(10);
        int wolfPeltChance = randomizer.getRandomInt(10);
        wolfLoot.add(wolfPeltChance > 7 ? "Wolf pelt" : "Wolf scraps");
        if (wolfPawChance > 7) {
            wolfLoot.add("Wolf paw");
        }
        return wolfLoot;
    }
    private List<String> determineSpiritLoot() {
        List<String> spiritLoot = new ArrayList<>();
        spiritLoot.add("Spirit trinket");
        return spiritLoot;
    }
    private List<String> determineOrcLoot(int potionsLeft) {
        List<String> orcLoot = new ArrayList<>();

        int vestChance = randomizer.getRandomInt(10);
        int pantsChance = randomizer.getRandomInt(10);
        int necklaceChance = randomizer.getRandomInt(10);
        int bootsChance = randomizer.getRandomInt(10);
        int helmChance = randomizer.getRandomInt(10);

        orcLoot.add("Sword");

        for (int i = 1; i < potionsLeft; i++){
            orcLoot.add("Potion");
        }

        if (vestChance > 9) {
            orcLoot.add("Vest");
        }
        if (pantsChance > 9) {
            orcLoot.add("Pants");
        }
        if (necklaceChance > 9) {
            orcLoot.add("Orc necklace");
        }
        if (bootsChance > 9) {
            orcLoot.add("Boots");
        }
        if (helmChance > 9) {
            orcLoot.add("Helm");
        }
        return orcLoot;
    }
    private List<String> determineThiefLoot(int potionsLeft) {
        List<String> thiefLoot = new ArrayList<>();

        int vestChance = randomizer.getRandomInt(10);
        int pantsChance = randomizer.getRandomInt(10);
        int necklaceChance = randomizer.getRandomInt(10);
        int bootsChance = randomizer.getRandomInt(10);
        int helmChance = randomizer.getRandomInt(10);

        thiefLoot.add("Dagger");

        for (int i = 1; i < potionsLeft; i++){
            thiefLoot.add("Potion");
        }

        if (vestChance > 9) {
            thiefLoot.add("Vest");
        }
        if (pantsChance > 9) {
            thiefLoot.add("Pants");
        }
        if (necklaceChance > 9) {
            thiefLoot.add("Jewels");
        }
        if (bootsChance > 9) {
            thiefLoot.add("Boots");
        }
        if (helmChance > 9) {
            thiefLoot.add("Mask");
        }
        return thiefLoot;
    }
}
