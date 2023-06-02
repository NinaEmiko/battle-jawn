package com.battlejawn.Battle.Jawn.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battlejawn.Battle.Jawn.DTO.CasterDTO;
import com.battlejawn.Battle.Jawn.DTO.DPSDTO;
import com.battlejawn.Battle.Jawn.DTO.HealerDTO;
import com.battlejawn.Battle.Jawn.DTO.InputDTO;
import com.battlejawn.Battle.Jawn.DTO.TankDTO;
import com.battlejawn.Battle.Jawn.Player.Caster;
import com.battlejawn.Battle.Jawn.Player.DPS;
import com.battlejawn.Battle.Jawn.Player.Healer;
import com.battlejawn.Battle.Jawn.Player.Tank;
import com.battlejawn.Battle.Jawn.Repository.TankRepository;

import com.battlejawn.Battle.Jawn.Repository.HealerRepository;

@Service
public class PlayerService {
    private final TankRepository tankRepository;
    private final HealerRepository healerRepository;

    @Autowired
    public PlayerService(TankRepository tankRepository) {
        this.tankRepository = tankRepository;
    }

    @Autowired
    public PlayerService(HealerRepository healerRepository) {
        this.healerRepository = healerRepository;
    }

    public void createTank(TankDTO tankDTO) {
        Tank tank = new Tank(tankDTO.getHealth(), tankDTO.getMaxHealth(), tankDTO.getStrength(),
                tankDTO.getPotions(), tankDTO.getMaxPotions(), tankDTO.getStatusAilments());

        tankRepository.save(tank);
    }

    public void createHealer(HealerDTO healerDTO) {
        Healer healer = new Healer(healerDTO.getHealth(), healerDTO.getMaxHealth(), healerDTO.getStrength(),
                healerDTO.getPotions(), healerDTO.getMaxPotions(), healerDTO.getStatusAilments());
        healerRepository.save(healer);
    }

    public void createCaster(CasterDTO casterDTO) {
        Caster caster = new Caster(casterDTO.getHealth(), casterDTO.getMaxHealth(), casterDTO.getStrength(),
                casterDTO.getPotions(), casterDTO.getMaxPotions(), casterDTO.getStatusAilments());
        casterRepository.save(caster);
    }

    public void createDPS(DPSDTO dpsDTO) {
        DPS dps = new DPS(dpsDTO.getHealth(), dpsDTO.getMaxHealth(), dpsDTO.getStrength(),
                dpsDTO.getPotions(), dpsDTO.getMaxPotions(), dpsDTO.getStatusAilments());
        dpsRepository.save(dps);
    }

    public void move(InputDTO input) {

    }
}