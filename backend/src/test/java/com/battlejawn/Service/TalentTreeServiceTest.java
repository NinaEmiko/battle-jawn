package com.battlejawn.Service;

import com.battlejawn.Entities.Hero.Caster;
import com.battlejawn.Entities.Hero.DPS;
import com.battlejawn.Entities.Hero.Healer;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Entities.TalentTree.CasterTree;
import com.battlejawn.Entities.TalentTree.DPSTree;
import com.battlejawn.Entities.TalentTree.HealerTree;
import com.battlejawn.Entities.TalentTree.TankTree;
import com.battlejawn.Repository.TalentTreeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TalentTreeServiceTest {
    @Mock
    Tank tank;
    @Mock
    Healer healer;
    @Mock
    Caster caster;
    @Mock
    DPS dps;
    @InjectMocks
    TalentTreeService talentTreeService;
    @Mock
    HeroService heroService;
    @Mock
    TankTree tankTree;
    @Mock
    HealerTree healerTree;
    @Mock
    DPSTree dpsTree;
    @Mock
    CasterTree casterTree;
    @Mock
    TalentTreeRepository talentTreeRepository;

    @Test
    void resetTankTalentsTest(){
        tank = new Tank("Tank");
        tank.setId(1);
        tankTree = (TankTree) tank.getTalentTree();

        when(talentTreeRepository.save(any())).thenReturn(tankTree);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        doNothing().when(heroService).updateHero(any());

        talentTreeService.resetTalents(tank.getId());
        Assertions.assertFalse(tankTree.isImprovedHealth2());
    }
    @Test
    void resetTankTalentsExceptionTest(){
        tank = new Tank("Tank");
        tank.setId(1);
        tankTree = (TankTree) tank.getTalentTree();

        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        when(talentTreeRepository.save(any())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            talentTreeService.resetTalents(tank.getId());
        });
    }

    @Test
    void resetHealerTalentsTest(){
        healer = new Healer("Healer");
        healer.setId(1);
        healerTree = (HealerTree) healer.getTalentTree();

        when(talentTreeRepository.save(any())).thenReturn(healerTree);
        when(heroService.getHeroById(anyLong())).thenReturn(healer);
        doNothing().when(heroService).updateHero(any());

        talentTreeService.resetTalents(healer.getId());
        Assertions.assertFalse(healerTree.isSpirituallyAttuned());
    }

    @Test
    void resetHealerTalentsExceptionTest(){
        healer = new Healer("Healer");
        healer.setId(1);
        healerTree = (HealerTree) healer.getTalentTree();

        when(heroService.getHeroById(anyLong())).thenReturn(healer);
        when(talentTreeRepository.save(any())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            talentTreeService.resetTalents(healer.getId());
        });
    }

    @Test
    void resetCasterTalentsTest(){
        caster = new Caster("Caster");
        caster.setId(1);
        casterTree = (CasterTree) caster.getTalentTree();

        when(talentTreeRepository.save(any())).thenReturn(casterTree);
        when(heroService.getHeroById(anyLong())).thenReturn(caster);
        doNothing().when(heroService).updateHero(any());

        talentTreeService.resetTalents(caster.getId());
        Assertions.assertFalse(casterTree.isImprovedFireBlast1());
    }

    @Test
    void resetCasterTalentsExceptionTest(){
        caster = new Caster("Caster");
        caster.setId(1);
        casterTree = (CasterTree) caster.getTalentTree();

        when(talentTreeRepository.save(any())).thenReturn(casterTree);
        when(talentTreeRepository.save(any())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            talentTreeService.resetTalents(caster.getId());
        });
    }

    @Test
    void resetDPSTalentsTest(){
        dps = new DPS("DPS");
        dps.setId(1);
        dpsTree = (DPSTree) dps.getTalentTree();

        when(talentTreeRepository.save(any())).thenReturn(dpsTree);
        when(heroService.getHeroById(anyLong())).thenReturn(dps);
        doNothing().when(heroService).updateHero(any());

        talentTreeService.resetTalents(dps.getId());
        Assertions.assertFalse(dpsTree.isElation());
    }

    @Test
    void resetDPSTalentsExceptionTest(){
        dps = new DPS("DPS");
        dps.setId(1);
        dpsTree = (DPSTree) dps.getTalentTree();

        when(talentTreeRepository.save(any())).thenReturn(dpsTree);
        when(talentTreeRepository.save(any())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            talentTreeService.resetTalents(dps.getId());
        });
    }

    @Test
    void activateTankTalentTest(){
        tank = new Tank("Tank");
        tank.setId(1);
        tank.setTalentPoints(14);
        tankTree = (TankTree) tank.getTalentTree();

        when(talentTreeRepository.save(any())).thenReturn(tankTree);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        doNothing().when(heroService).updateHero(any());

        talentTreeService.activateTalent(tank.getId(), "Improved Health 1");
        talentTreeService.activateTalent(tank.getId(), "Improved Health 2");
        talentTreeService.activateTalent(tank.getId(), "Improved Block 1");
        talentTreeService.activateTalent(tank.getId(), "Improved Block 2");
        talentTreeService.activateTalent(tank.getId(), "Hydration");
        talentTreeService.activateTalent(tank.getId(), "Final Stand");
        talentTreeService.activateTalent(tank.getId(), "Desperation");
        talentTreeService.activateTalent(tank.getId(), "Titan");
        talentTreeService.activateTalent(tank.getId(), "Improved Strike 1");
        talentTreeService.activateTalent(tank.getId(), "Improved Strike 2");
        talentTreeService.activateTalent(tank.getId(), "Improved Strike 3");
        talentTreeService.activateTalent(tank.getId(), "Improved Impale 1");
        talentTreeService.activateTalent(tank.getId(), "Improved Impale 2");
        talentTreeService.activateTalent(tank.getId(), "Improved Impale 3");
        talentTreeService.activateTalent(tank.getId(), "Default");

        Assertions.assertTrue(tankTree.isImprovedHealth2());
    }

    @Test
    void activateHealerTalentTest(){
        healer = new Healer("Healer");
        healer.setId(1);
        healer.setTalentPoints(14);
        healerTree = (HealerTree) healer.getTalentTree();

        when(talentTreeRepository.save(any())).thenReturn(healerTree);
        when(heroService.getHeroById(anyLong())).thenReturn(healer);
        doNothing().when(heroService).updateHero(any());

        talentTreeService.activateTalent(healer.getId(), "Improved Holy 1");
        talentTreeService.activateTalent(healer.getId(), "Improved Holy 2");
        talentTreeService.activateTalent(healer.getId(), "Improved Holy 3");
        talentTreeService.activateTalent(healer.getId(), "Botany 1");
        talentTreeService.activateTalent(healer.getId(), "Botany 2");
        talentTreeService.activateTalent(healer.getId(), "Bubble");
        talentTreeService.activateTalent(healer.getId(), "Survival Instincts");
        talentTreeService.activateTalent(healer.getId(), "Spiritually Attuned");
        talentTreeService.activateTalent(healer.getId(), "Improved Wand 1");
        talentTreeService.activateTalent(healer.getId(), "Improved Wand 2");
        talentTreeService.activateTalent(healer.getId(), "Improved Wand 3");
        talentTreeService.activateTalent(healer.getId(), "Improved Heal 1");
        talentTreeService.activateTalent(healer.getId(), "Improved Heal 2");
        talentTreeService.activateTalent(healer.getId(), "Improved Heal 3");
        talentTreeService.activateTalent(healer.getId(), "Default");

        Assertions.assertTrue(healerTree.isSpirituallyAttuned());    }

    @Test
    void activateCasterTalentTest(){
        caster = new Caster("Caster");
        caster.setId(1);
        caster.setTalentPoints(14);
        casterTree = (CasterTree) caster.getTalentTree();

        when(talentTreeRepository.save(any())).thenReturn(casterTree);
        when(heroService.getHeroById(anyLong())).thenReturn(caster);
        doNothing().when(heroService).updateHero(any());

        talentTreeService.activateTalent(caster.getId(), "Improved FireBlast 1");
        talentTreeService.activateTalent(caster.getId(), "Improved FireBlast 2");
        talentTreeService.activateTalent(caster.getId(), "Improved IceBlast");
        talentTreeService.activateTalent(caster.getId(), "Resourcefulness 1");
        talentTreeService.activateTalent(caster.getId(), "Resourcefulness 2");
        talentTreeService.activateTalent(caster.getId(), "FrostBite");
        talentTreeService.activateTalent(caster.getId(), "Botany 1");
        talentTreeService.activateTalent(caster.getId(), "Botany 2");
        talentTreeService.activateTalent(caster.getId(), "Improved Wand 1");
        talentTreeService.activateTalent(caster.getId(), "Improved Wand 2");
        talentTreeService.activateTalent(caster.getId(), "Improved Wand 3");
        talentTreeService.activateTalent(caster.getId(), "Botany 3");
        talentTreeService.activateTalent(caster.getId(), "Second Nature");
        talentTreeService.activateTalent(caster.getId(), "Preparation");
        talentTreeService.activateTalent(caster.getId(), "Default");

        Assertions.assertTrue(casterTree.isImprovedFireBlast1());
    }

    @Test
    void activateDPSTalentTest(){
        dps = new DPS("DPS");
        dps.setId(1);
        dps.setTalentPoints(14);
        dpsTree = (DPSTree) dps.getTalentTree();

        when(talentTreeRepository.save(any())).thenReturn(dpsTree);
        when(heroService.getHeroById(anyLong())).thenReturn(dps);
        doNothing().when(heroService).updateHero(any());

        talentTreeService.activateTalent(dps.getId(), "Improved BackStab 1");
        talentTreeService.activateTalent(dps.getId(), "Improved BackStab 2");
        talentTreeService.activateTalent(dps.getId(), "Improved Steal 1");
        talentTreeService.activateTalent(dps.getId(), "Improved Steal 2");
        talentTreeService.activateTalent(dps.getId(), "Sticky Fingaz");
        talentTreeService.activateTalent(dps.getId(), "Elation");
        talentTreeService.activateTalent(dps.getId(), "Organized Mess");
        talentTreeService.activateTalent(dps.getId(), "Honor Among Thieves");
        talentTreeService.activateTalent(dps.getId(), "Improved Stab 1");
        talentTreeService.activateTalent(dps.getId(), "Improved Stab 2");
        talentTreeService.activateTalent(dps.getId(), "Improved Stab 3");
        talentTreeService.activateTalent(dps.getId(), "First Strike");
        talentTreeService.activateTalent(dps.getId(), "Peekaboo");
        talentTreeService.activateTalent(dps.getId(), "Energized");
        talentTreeService.activateTalent(dps.getId(), "");

        Assertions.assertTrue(dpsTree.isElation());
    }
}
