package main.java.com.battlejawn.Battle.Jawn.Controllers;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("../PlayerAction/PlayerAction")
    public void playerAction(@RequestBody InputDTO input) {
        playerService.move(input);
    }

    @PostMapping("../Player/Tank")
    public ResponseEntity<String> createTank(@RequestBody TankDTO tankDTO) {
        try {
            playerService.createTank(tankDTO);
            return ResponseEntitiy.ok("Tank created successfully");
        } catch (Eception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Tank");
        }
    }

    @PostMapping("../Player/Healer")
    public ResponseEntity<String> createHealer(@RequestBody HealerDTO healerDTO) {
        try {
            playerService.createHealer(healerDTO);
            return ResponseEntitiy.ok("Healer created successfully");
        } catch (Eception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Healer");
        }
    }

    @PostMapping("../Player/Caster")
    public ResponseEntity<String> createCaster(@RequestBody CasterDTO casterDTO) {
        try {
            playerService.createCaster(casterDTO);
            return ResponseEntitiy.ok("Caster created successfully");
        } catch (Eception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Caster");
        }
    }

    @PostMapping("../Player/DPS")
    public ResponseEntity<String> createDPS(@RequestBody DPSDTO dpsDTO) {
        try {
            playerService.createDPS(dpsDTO);
            return ResponseEntitiy.ok("DPS created successfully");
        } catch (Eception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create DPS");
        }
    }
}
