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

    public String activateTalent(Long heroId, String talent) {

        Hero hero = heroService.getHeroById(heroId);
        if (hero.getTalentPoints() < 1){
            return "You do not have enough talent points to activate this talent.";
        }

        return switch (hero.getRole()) {
            case "Tank" -> {
                processTankTalent(hero, talent);
                yield "Talent successfully activated: " + talent + " .";
            }
            case "Healer" -> {
                processHealerTalent(hero, talent);
                yield "Talent successfully activated: " + talent + " .";
            }
            case "DPS" -> {
                processDPSTalent(hero, talent);
                yield "Talent successfully activated: " + talent + " .";
            }
            case "Caster" -> {
                processCasterTalent(hero, talent);
                yield "Talent successfully activated: " + talent + " .";
            }
            default -> "Error occurred while activating talent";
        };
    }
    private void processTankTalent(Hero hero, String talent) {
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
            case "improvedStrike2":
                tankTree.setImprovedStrike2(true);
                break;
            case "improvedStrike3":
                tankTree.setImprovedStrike3(true);
                break;
            case "titan":
                tankTree.setTitan(true);
                break;
            case "desperation":
                tankTree.setDesperation(true);
                break;
            case "finalStand":
                tankTree.setFinalStand(true);
                break;
            case "hydration":
                tankTree.setHydration(true);
                break;
            case "improvedBlock1":
                tankTree.setImprovedBlock1(true);
                break;
            case "improvedBlock2":
                tankTree.setImprovedBlock2(true);
                break;
            case "improvedHealth1":
                tankTree.setImprovedHealth1(true);
                break;
            case "improvedHealth2":
                tankTree.setImprovedHealth2(true);
                break;
        }
        talentTreeRepository.save(tankTree);
    }

    private void processHealerTalent(Hero hero, String talent) {
        HealerTree healerTree = (HealerTree) hero.getTalentTree();

            switch (talent) {
                case "botany1":
                    healerTree.setBotany1(true);
                    break;
                case "botany2":
                    healerTree.setBotany2(true);
                    break;
                case "improvedHeal1":
                    healerTree.setImprovedHeal1(true);
                    break;
                case "improvedHeal2":
                    healerTree.setImprovedHeal2(true);
                    break;
                case "improvedHeal3":
                    healerTree.setImprovedHeal3(true);
                    break;
                case "spirituallyAttuned":
                    healerTree.setSpirituallyAttuned(true);
                    break;
                case "survivalInstincts":
                    healerTree.setSurvivalInstincts(true);
                    break;
                case "bubble":
                    healerTree.setBubble(true);
                    break;
                case "improvedHoly1":
                    healerTree.setImprovedHoly1(true);
                    break;
                case "improvedHoly2":
                    healerTree.setImprovedHoly2(true);
                    break;
                case "improvedHoly3":
                    healerTree.setImprovedHoly3(true);
                    break;
                case "improvedWand1":
                    healerTree.setImprovedWand1(true);
                    break;
                case "improvedWand2":
                    healerTree.setImprovedWand2(true);
                    break;
                case "improvedWand3":
                    healerTree.setImprovedWand3(true);
                    break;
            }
        hero.setTalentPoints(hero.getTalentPoints() - 1);
        heroService.updateHero(hero);
        talentTreeRepository.save(healerTree);
    }

    private void processDPSTalent(Hero hero, String talent) {
        DPSTree dpsTree = (DPSTree) hero.getTalentTree();

            switch (talent) {
                case "energized":
                    dpsTree.setEnergized(true);
                    break;
                case "improvedBackStab1":
                    dpsTree.setImprovedBackStab1(true);
                    break;
                case "improvedBackStab2":
                    dpsTree.setImprovedBackStab2(true);
                    break;
                case "improvedStab1":
                    dpsTree.setImprovedStab1(true);
                    break;
                case "improvedStab2":
                    dpsTree.setImprovedStab2(true);
                    break;
                case "improvedStab3":
                    dpsTree.setImprovedStab3(true);
                    break;
                case "peekaboo":
                    dpsTree.setPeekaboo(true);
                    break;
                case "elation":
                    dpsTree.setElation(true);
                    break;
                case "firstStrike":
                    dpsTree.setFirstStrike(true);
                    break;
                case "HonorAmongThieves":
                    dpsTree.setHonorAmongThieves(true);
                    break;
                case "improvedSteal1":
                    dpsTree.setImprovedSteal1(true);
                    break;
                case "improvedSteal2":
                    dpsTree.setImprovedSteal2(true);
                    break;
                case "organizedMess":
                    dpsTree.setOrganizedMess(true);
                    break;
                case "stickyFingaz":
                    dpsTree.setStickyFingaz(true);
                    break;
            }
        hero.setTalentPoints(hero.getTalentPoints() - 1);
        heroService.updateHero(hero);
        talentTreeRepository.save(dpsTree);
    }
    private void processCasterTalent(Hero hero, String talent) {
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
        }
        hero.setTalentPoints(hero.getTalentPoints() - 1);
        heroService.updateHero(hero);
        talentTreeRepository.save(casterTree);
    }
}
