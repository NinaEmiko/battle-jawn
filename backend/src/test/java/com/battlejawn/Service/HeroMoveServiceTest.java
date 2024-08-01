package com.battlejawn.Service;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Battle.BattleStatus;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Enemy.Orc;
import com.battlejawn.Entities.Hero.*;
import com.battlejawn.Entities.Inventory;
import com.battlejawn.HeroMove.Attack.Stab;
import com.battlejawn.HeroMove.Attack.Strike;
import com.battlejawn.HeroMove.Attack.Wand;
import com.battlejawn.HeroMove.Heal.Potion;
import com.battlejawn.HeroMove.Run;
import com.battlejawn.HeroMove.Steal;
import com.battlejawn.Repository.EnemyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HeroMoveServiceTest {
    @Mock
    BattleHistoryMessageService battleHistoryMessageService;
    @Mock
    BattleSessionService battleSessionService;
    @Mock
    Wand wand;
    @Mock
    Strike strike;
    @Mock
    Stab stab;
    @Mock
    HeroService heroService;
    @Mock
    EnemyService enemyService;
    @Mock
    HeroMoveDTO heroMoveDTO;
    @Mock
    Inventory inventory;
    @Mock
    InventoryService inventoryService;
    @Mock
    BattleSession battleSession;
    @Mock
    Enemy enemy;
    @Mock
    Tank tank;
    @Mock
    Healer healer;
    @Mock
    DPS dps;
    @Mock
    Caster caster;
    @Mock
    Run run;
    @Mock
    Steal steal;
    @Mock
    Potion potion;
    @Mock
    BattleStatusService battleStatusService;
    @Mock
    BattleHistoryMessage battleHistoryMessage;
    @Mock
    List<String> battleHistoryMessageList;
    @InjectMocks
    HeroMoveService heroMoveService;
    @BeforeEach
    void setup(){
        inventory = new Inventory();
        battleHistoryMessageList = new ArrayList<>();
        enemy = new Orc(2, 105, 2, 25);
        tank = new Tank("Name");
        healer = new Healer("Name");
        dps = new DPS("Name");
        caster = new Caster("Name");
    }
    @Test
    void heroMoveWandTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(caster);
        when(wand.attack()).thenReturn(1);


        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Wand", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveStrikeTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);
        when(strike.attack()).thenReturn(1);


        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Strike", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveStabTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(dps);
        when(stab.attack()).thenReturn(1);


        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Stab", 1L);

        assertNotNull(heroMoveDTO);
    }

    @Test
    void heroMoveStabMissTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(dps);
        when(stab.attack()).thenReturn(0);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Stab", 1L);

        assertNotNull(heroMoveDTO);
    }

    @Test
    void heroMoveFireBlastTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(caster);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("FireBlast", 1L);

        assertNotNull(heroMoveDTO);
    }

    @Test
    void heroMoveIceBlastTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(caster);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("IceBlast", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveIceBlastNoMagicTest() {
        caster.setResource(0);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(caster);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("IceBlast", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveBlockTest() {
        BattleStatus blockedStatus = new BattleStatus();
        BattleSession blockedSession = new BattleSession();
        blockedSession.setBattleStatus(blockedStatus);
        enemy.setId(6L);
        blockedSession.setEnemyId(6L);
        tank.setId(7L);
        blockedSession.setHeroId(7L);

        when(battleStatusService.saveBattleStatus(any())).thenReturn(blockedStatus);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(blockedSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Block", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveBlockNoPowerTest() {
        BattleStatus blockedStatus = new BattleStatus();
        BattleSession blockedSession = new BattleSession();
        blockedSession.setBattleStatus(blockedStatus);
        enemy.setId(6L);
        blockedSession.setEnemyId(6L);
        tank.setId(7L);
        tank.setResource(0);
        blockedSession.setHeroId(7L);

        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(blockedSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Block", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveNoWaterTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Water", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveWaterTankMaxPowerTest() {
        tank.setResource(3);
        tank.setMaxResource(3);
        tank.setRole("Tank");
        when(inventoryService.findItemCount(any(),anyString())).thenReturn(1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Water", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveWaterTankTest() {
        tank.setResource(2);
        tank.setMaxResource(3);
        tank.setRole("Tank");
        when(inventoryService.findItemCount(any(),anyString())).thenReturn(1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Water", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveWaterHealerMaxSpiritTest() {
        healer.setResource(3);
        healer.setMaxResource(3);
        healer.setRole("Healer");
        when(inventoryService.findItemCount(any(),anyString())).thenReturn(1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(healer);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Water", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveWaterHealerTest() {
        healer.setResource(2);
        healer.setMaxResource(3);
        healer.setRole("Healer");
        when(inventoryService.findItemCount(any(),anyString())).thenReturn(1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(healer);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Water", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveWaterCasterMaxMagicTest() {
        caster.setResource(2);
        caster.setMaxResource(3);
        caster.setRole("Caster");
        when(inventoryService.findItemCount(any(),anyString())).thenReturn(1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(caster);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Water", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveWaterCasterTest() {
        caster.setResource(3);
        caster.setMaxResource(3);
        caster.setRole("Caster");
        when(inventoryService.findItemCount(any(),anyString())).thenReturn(1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(caster);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Water", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveWaterDPSMaxEnergyTest() {
        dps.setResource(3);
        dps.setMaxResource(3);
        dps.setRole("DPS");
        when(inventoryService.findItemCount(any(),anyString())).thenReturn(1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(dps);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Water", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveWaterDPSTest() {
        dps.setResource(2);
        dps.setMaxResource(3);
        dps.setRole("DPS");
        when(inventoryService.findItemCount(any(),anyString())).thenReturn(1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(dps);
        doNothing().when(heroService).updateHero(any());
        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Water", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveBlastTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(caster);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Blast", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveHolyTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(healer);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Holy", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveHolyNoSpiritTest() {
        healer.setResource(0);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(healer);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Holy", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveImpaleTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Impale", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveImpaleNoPowerTest() {
        tank.setResource(0);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Impale", 1L);

        assertNotNull(heroMoveDTO);
    }

    @Test
    void heroMoveBackStabTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(dps);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("BackStab", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveHealTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(healer);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Heal", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveHealNoSpiritTest() {
        healer.setResource(0);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(healer);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Heal", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMovePotionTest() {
        tank.setHealth(1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);

        when(inventoryService.findItemCount(inventory, "Potion")).thenReturn(1);
        doNothing().when(inventoryService).removeFirstFromInventory(anyLong(), anyString());
        when(potion.usePotion()).thenReturn(1);
        doNothing().when(heroService).updateHero(any());
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Potion", 1L);

        assertNotNull(heroMoveDTO);
    }

    @Test
    void heroMovePotionAlmostMaxHealthTest() {
        tank.setHealth(tank.getMaxHealth() - 1);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);

        when(inventoryService.findItemCount(inventory, "Potion")).thenReturn(1);
        doNothing().when(inventoryService).removeFirstFromInventory(anyLong(), anyString());
        when(potion.usePotion()).thenReturn(30);
        doNothing().when(heroService).updateHero(any());
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Potion", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMovePotionMaxHealthTest() {
        tank.setHealth(tank.getMaxHealth());
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);

        when(inventoryService.findItemCount(inventory, "Potion")).thenReturn(1);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Potion", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveNoPotionTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(tank);

        when(inventoryService.findItemCount(inventory, "Potion")).thenReturn(0);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Potion", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveStealTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(dps);

        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(1);
        when(steal.useSteal()).thenReturn(true);
        doNothing().when(inventoryService).addToFirstEmptySlot(any(),anyString());
        doNothing().when(heroService).updateHero(any());
        doNothing().when(enemyService).updatePotionCountById(1, null);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Steal", 1L);
        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveStealNoEnergyTest() {
        dps.setResource(0);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(dps);

        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(1);
        when(steal.useSteal()).thenReturn(true);
        doNothing().when(inventoryService).addToFirstEmptySlot(any(),anyString());
        doNothing().when(heroService).updateHero(any());
        doNothing().when(enemyService).updatePotionCountById(1, null);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Steal", 1L);
        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveStealFailTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(dps);

        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(1);
        when(steal.useSteal()).thenReturn(false);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Steal", 1L);
        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveStealNoPotionsTest() {
        enemy.setPotions(0);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(dps);

        when(inventoryService.getEmptySlotSize(anyLong())).thenReturn(1);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Steal", 1L);
        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveRunTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        when(heroService.getHeroById(anyLong())).thenReturn(dps);

        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove("Run", 1L);

        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveRunFailTest() {
        when(run.useRun()).thenReturn(false);
        when(battleHistoryMessageService.createNewMessage(anyLong(), any())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
        HeroMoveDTO heroMoveDTO = heroMoveService.processRun(enemy, 1L, dps);
        assertNotNull(heroMoveDTO);
    }
    @Test
    void heroMoveRunSuccessTest() {
        when(run.useRun()).thenReturn(true);
        doNothing().when(heroService).updateHero(dps);
        when(battleHistoryMessageService.createNewMessage(anyLong(), any())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);
        HeroMoveDTO heroMoveDTO = heroMoveService.processRun(enemy, 1L, dps);
        assertNotNull(heroMoveDTO);
    }

    @Test
    void processHeroHeal() {
        healer.setHealth(100);
        healer.setId(2L);

        doNothing().when(heroService).updateHero(healer);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        heroMoveService.processHeroHeal(1, enemy, 1L, healer);

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());

    }

    @Test
    void processHeroMaxHealthHeal() {
        healer.setId(2L);

        doNothing().when(heroService).updateHero(healer);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        heroMoveService.processHeroHeal(1, enemy, 1L, healer);

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());

    }

    @Test
    void getDamageMessageMissedTest() {
        String result = heroMoveService.getDamageMessage("Move", 0);
        Assertions.assertEquals(result, "Move missed!");
    }
    @Test
    void getDamageMessageHitTest() {
        String result = heroMoveService.getDamageMessage("Move", 1);
        Assertions.assertEquals(result, "Move did 1 damage.");
    }

    @Test
    void processHeroMoveDefeatTest() {
        enemy.setId(1L);
        healer.setId(2L);
        doNothing().when(enemyService).updateHealthById(0, 1L);
        doNothing().when(heroService).updateHero(healer);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        heroMoveService.processHeroAttack(1000, enemy, 1L, healer, "Move");

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());

    }

    @Test
    void processHeroMoveDamageTest() {
        enemy.setId(1L);
        tank.setId(2L);
        doNothing().when(enemyService).updateHealthById(95, 1L);
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        heroMoveService.processHeroAttack(10, enemy, 1L, tank, "Strike");

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
    }
    @Test
    void processHeroMoveNoPowerDamageTest() {
        enemy.setId(1L);
        healer.setId(2L);
        healer.setResource(0);
        healer.setRole("Healer");
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        heroMoveService.processHeroAttack(10, enemy, 1L, healer, "Holy");

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
    }
    @Test
    void processHeroMoveNoPowerCasterDamageTest() {
        enemy.setId(1L);
        caster.setId(2L);
        caster.setResource(0);
        caster.setRole("Caster");
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        heroMoveService.processHeroAttack(10, enemy, 1L, caster, "Wand");

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
    }
    @Test
    void processHeroMoveNoPowerDPSDamageTest() {
        enemy.setId(1L);
        dps.setId(2L);
        dps.setResource(0);
        dps.setRole("DPS");
        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        heroMoveService.processHeroAttack(10, enemy, 1L, dps, "BackStab");

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
    }
    @Test
    void processHeroMoveDefeatEnemyTest() {
        enemy.setId(1L);
        enemy.setHealth(1);
        dps.setId(2L);
        dps.setResource(0);
        dps.setRole("DPS");

        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        heroMoveService.processHeroAttack(10, enemy, 1L, dps, "Stab");

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
    }
    @Test
    void processHeroMoveHurtEnemyTest() {
        enemy.setId(1L);
        enemy.setHealth(11);
        dps.setId(2L);
        dps.setResource(0);
        dps.setRole("DPS");

        when(battleHistoryMessageService.createNewMessage(anyLong(), anyString())).thenReturn(battleHistoryMessage);
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessageList);

        heroMoveService.processHeroAttack(10, enemy, 1L, dps, "Strike");

        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
    }
}