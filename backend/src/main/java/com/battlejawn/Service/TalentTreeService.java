package com.battlejawn.Service;

import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.TalentTree.CasterTree;
import com.battlejawn.Entities.TalentTree.DPSTree;
import com.battlejawn.Entities.TalentTree.HealerTree;
import com.battlejawn.Entities.TalentTree.TankTree;
import com.battlejawn.Repository.TalentTreeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class TalentTreeService {
    private TalentTreeRepository talentTreeRepository;
    private HeroService heroService;
    private final Logger logger = Logger.getLogger(TalentTreeService.class.getName());

    public Hero resetTalents(Long heroId) {
        Hero hero = heroService.getHeroById(heroId);
        switch (hero.getRole()) {
            case "Tank" -> resetTankTalents(hero);
            case "Healer" -> resetHealerTalents(hero);
            case "DPS" -> resetDPSTalents(hero);
            case "Caster" -> resetCasterTalents(hero);
        };
        return hero;
    }

    public String activateTalent(Long heroId, String talent) {

        Hero hero = heroService.getHeroById(heroId);
        if (hero.getTalentPoints() < 1){
            return "You do not have enough talent points to activate this talent.";
        }

        return switch (hero.getRole()) {
            case "Tank" -> processTankTalent(hero, talent);
            case "Healer" -> processHealerTalent(hero, talent);
            case "DPS" -> processDPSTalent(hero, talent);
            case "Caster" -> processCasterTalent(hero, talent);
            default -> "Error occurred while activating talent";
        };
    }

    private String resetHealerTalents(Hero hero){
        try {
            HealerTree healerTree = (HealerTree) hero.getTalentTree();
            healerTree.setImprovedHoly1(false);
            healerTree.setImprovedHoly2(false);
            healerTree.setImprovedHoly3(false);
            healerTree.setImprovedHeal1(false);
            healerTree.setImprovedHeal2(false);
            healerTree.setImprovedHeal3(false);
            healerTree.setImprovedWand1(false);
            healerTree.setImprovedWand2(false);
            healerTree.setImprovedWand3(false);
            healerTree.setBubble(false);
            healerTree.setBotany1(false);
            healerTree.setBotany2(false);
            healerTree.setSurvivalInstincts(false);
            healerTree.setSpirituallyAttuned(false);
            talentTreeRepository.save(healerTree);
            hero.setTalentPoints(hero.getLevel() - 1);
            heroService.updateHero(hero);
            return hero.getName() + "'s talents successfully reset.";
        } catch (Exception e) {
            throw new RuntimeException("TalentTreeService failed to reset talents for hero with ID: " + hero.getId() + ". Error: " + e.getMessage() + ".");
        }
    }

    private String resetTankTalents(Hero hero){
        try {
            TankTree tankTree = (TankTree) hero.getTalentTree();
            if (tankTree.isImprovedHealth1()){
                hero.setMaxHealth(hero.getMaxHealth() - 5);
            }
            if (tankTree.isImprovedHealth2()){
                hero.setMaxHealth(hero.getMaxHealth() - 5);
            }
            tankTree.setImprovedHealth1(false);
            tankTree.setImprovedHealth2(false);
            tankTree.setTitan(false);
            tankTree.setHydration(false);
            tankTree.setDesperation(false);
            tankTree.setFinalStand(false);
            tankTree.setImprovedImpale1(false);
            tankTree.setImprovedImpale2(false);
            tankTree.setImprovedImpale3(false);
            tankTree.setImprovedStrike1(false);
            tankTree.setImprovedStrike2(false);
            tankTree.setImprovedStrike3(false);
            tankTree.setImprovedBlock1(false);
            tankTree.setImprovedBlock2(false);
            talentTreeRepository.save(tankTree);
            hero.setTalentPoints(hero.getLevel() - 1);
            heroService.updateHero(hero);
            return hero.getName() + "'s talents successfully reset.";
        } catch (Exception e) {
            throw new RuntimeException("TalentTreeService failed to reset talents for hero with ID: " + hero.getId() + ". Error: " + e.getMessage() + ".");
        }
    }

    private String resetDPSTalents(Hero hero){
        try {
            DPSTree dpsTree = (DPSTree) hero.getTalentTree();
            dpsTree.setStickyFingaz(false);
            dpsTree.setElation(false);
            dpsTree.setPeekaboo(false);
            dpsTree.setEnergized(false);
            dpsTree.setFirstStrike(false);
            dpsTree.setOrganizedMess(false);
            dpsTree.setHonorAmongThieves(false);
            dpsTree.setImprovedSteal1(false);
            dpsTree.setImprovedSteal2(false);
            dpsTree.setImprovedStab1(false);
            dpsTree.setImprovedStab2(false);
            dpsTree.setImprovedStab3(false);
            dpsTree.setImprovedBackStab1(false);
            dpsTree.setImprovedBackStab2(false);
            talentTreeRepository.save(dpsTree);
            hero.setTalentPoints(hero.getLevel() - 1);
            heroService.updateHero(hero);
            return hero.getName() + "'s talents successfully reset.";
        } catch (Exception e) {
            throw new RuntimeException("TalentTreeService failed to reset talents for hero with ID: " + hero.getId() + ". Error: " + e.getMessage() + ".");
        }
    }

    private String resetCasterTalents(Hero hero){
        try {
            CasterTree casterTree = (CasterTree) hero.getTalentTree();
            casterTree.setSecondNature(false);
            casterTree.setPreparation(false);
            casterTree.setFrostBite(false);
            casterTree.setImprovedIceBlast(false);
            casterTree.setBotany1(false);
            casterTree.setBotany2(false);
            casterTree.setBotany3(false);
            casterTree.setImprovedWand1(false);
            casterTree.setImprovedWand2(false);
            casterTree.setImprovedWand3(false);
            casterTree.setResourcefulness1(false);
            casterTree.setResourcefulness2(false);
            casterTree.setImprovedFireBlast1(false);
            casterTree.setImprovedFireBlast2(false);
            talentTreeRepository.save(casterTree);
            hero.setTalentPoints(hero.getLevel() - 1);
            heroService.updateHero(hero);
            return hero.getName() + "'s talents successfully reset.";
        } catch (Exception e) {
            throw new RuntimeException("TalentTreeService failed to reset talents for hero with ID: " + hero.getId() + ". Error: " + e.getMessage() + ".");
        }
    }

    private String processTankTalent(Hero hero, String talent) {
        TankTree tankTree = (TankTree) hero.getTalentTree();

        switch (talent) {
            case "Improved Impale 1":
                tankTree.setImprovedImpale1(true);
                break;
            case "Improved Impale 2":
                tankTree.setImprovedImpale2(true);
                break;
            case "Improved Impale 3":
                tankTree.setImprovedImpale3(true);
                break;
            case "Improved Strike 1":
                tankTree.setImprovedStrike1(true);
                break;
            case "Improved Strike 2":
                tankTree.setImprovedStrike2(true);
                break;
            case "Improved Strike 3":
                tankTree.setImprovedStrike3(true);
                break;
            case "Titan":
                tankTree.setTitan(true);
                break;
            case "Desperation":
                tankTree.setDesperation(true);
                break;
            case "Final Stand":
                tankTree.setFinalStand(true);
                break;
            case "Hydration":
                tankTree.setHydration(true);
                break;
            case "Improved Block 1":
                tankTree.setImprovedBlock1(true);
                break;
            case "Improved Block 2":
                tankTree.setImprovedBlock2(true);
                break;
            case "Improved Health 1":
                hero.setMaxHealth(hero.getMaxHealth() + 5);
                tankTree.setImprovedHealth1(true);
                break;
            case "Improved Health 2":
                hero.setMaxHealth(hero.getMaxHealth() + 5);
                tankTree.setImprovedHealth2(true);
                break;
            default:
                return "Error occurred while activating " + talent + ".";
        }
        talentTreeRepository.save(tankTree);
        return "Successfully activated " + talent + ".";
    }

    private String processHealerTalent(Hero hero, String talent) {
        HealerTree healerTree = (HealerTree) hero.getTalentTree();

            switch (talent) {
                case "Botany 1":
                    healerTree.setBotany1(true);
                    break;
                case "Botany 2":
                    healerTree.setBotany2(true);
                    break;
                case "Improved Heal 1":
                    healerTree.setImprovedHeal1(true);
                    break;
                case "Improved Heal 2":
                    healerTree.setImprovedHeal2(true);
                    break;
                case "Improved Heal 3":
                    healerTree.setImprovedHeal3(true);
                    break;
                case "Spiritually Attuned":
                    healerTree.setSpirituallyAttuned(true);
                    break;
                case "Survival Instincts":
                    healerTree.setSurvivalInstincts(true);
                    break;
                case "Bubble":
                    healerTree.setBubble(true);
                    break;
                case "Improved Holy 1":
                    healerTree.setImprovedHoly1(true);
                    break;
                case "Improved Holy 2":
                    healerTree.setImprovedHoly2(true);
                    break;
                case "Improved Holy 3":
                    healerTree.setImprovedHoly3(true);
                    break;
                case "Improved Wand 1":
                    healerTree.setImprovedWand1(true);
                    break;
                case "Improved Wand 2":
                    healerTree.setImprovedWand2(true);
                    break;
                case "Improved Wand 3":
                    healerTree.setImprovedWand3(true);
                    break;
                default:
                    return "Error occurred while activating " + talent + ".";
            }
        hero.setTalentPoints(hero.getTalentPoints() - 1);
        heroService.updateHero(hero);
        talentTreeRepository.save(healerTree);
        return "Successfully activated " + talent + ".";
    }

    private String processDPSTalent(Hero hero, String talent) {
        DPSTree dpsTree = (DPSTree) hero.getTalentTree();

            switch (talent) {
                case "Energized":
                    dpsTree.setEnergized(true);
                    break;
                case "Improved BackStab 1":
                    dpsTree.setImprovedBackStab1(true);
                    break;
                case "Improved BackStab 2":
                    dpsTree.setImprovedBackStab2(true);
                    break;
                case "Improved Stab 1":
                    dpsTree.setImprovedStab1(true);
                    break;
                case "Improved Stab 2":
                    dpsTree.setImprovedStab2(true);
                    break;
                case "Improved Stab 3":
                    dpsTree.setImprovedStab3(true);
                    break;
                case "Peekaboo":
                    dpsTree.setPeekaboo(true);
                    break;
                case "Elation":
                    dpsTree.setElation(true);
                    break;
                case "First Strike":
                    dpsTree.setFirstStrike(true);
                    break;
                case "Honor Among Thieves":
                    dpsTree.setHonorAmongThieves(true);
                    break;
                case "Improved Steal 1":
                    dpsTree.setImprovedSteal1(true);
                    break;
                case "mproved Steal 2":
                    dpsTree.setImprovedSteal2(true);
                    break;
                case "Organized Mess":
                    dpsTree.setOrganizedMess(true);
                    break;
                case "Sticky Fingaz":
                    dpsTree.setStickyFingaz(true);
                    break;
                default:
                    return "Error occurred while activating " + talent + ".";
            }
        hero.setTalentPoints(hero.getTalentPoints() - 1);
        heroService.updateHero(hero);
        talentTreeRepository.save(dpsTree);
        return "Successfully activated " + talent + ".";
    }
    private String processCasterTalent(Hero hero, String talent) {
        CasterTree casterTree = (CasterTree) hero.getTalentTree();

        switch (talent) {
            case "FrostBite":
                casterTree.setFrostBite(true);
                break;
            case "Improved FireBlast 1":
                casterTree.setImprovedFireBlast1(true);
                break;
            case "Improved FireBlast 2":
                casterTree.setImprovedFireBlast2(true);
                break;
            case "Improved IceBlast":
                casterTree.setImprovedIceBlast(true);
                break;
            case "Improved Wand 1":
                casterTree.setImprovedWand1(true);
                break;
            case "Improved Wand 2":
                casterTree.setImprovedWand2(true);
                break;
            case "Improved Wand 3":
                casterTree.setImprovedWand3(true);
                break;
            case "Botany 1":
                casterTree.setBotany1(true);
                break;
            case "Botany 2":
                casterTree.setBotany2(true);
                break;
            case "Botany 3":
                casterTree.setBotany3(true);
                break;
            case "Preparation":
                casterTree.setPreparation(true);
                break;
            case "Resourcefulness 1":
                casterTree.setResourcefulness1(true);
                break;
            case "Resourcefulness 2":
                casterTree.setResourcefulness2(true);
                break;
            case "Second Nature":
                casterTree.setSecondNature(true);
                break;
            default:
                return "Error occurred while activating " + talent + ".";
        }
        hero.setTalentPoints(hero.getTalentPoints() - 1);
        heroService.updateHero(hero);
        talentTreeRepository.save(casterTree);
        return "Successfully activated " + talent + ".";
    }
}
