package main.java.com.battlejawn.Battle.Jawn.Service;

import main.java.com.battlejawn.Battle.Jawn.DTO.TankDTO;

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