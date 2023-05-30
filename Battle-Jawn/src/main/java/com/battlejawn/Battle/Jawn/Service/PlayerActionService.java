package main.java.com.battlejawn.Battle.Jawn.Service;

@Service
public class PlayerActionService {
    private final PlayerService playerService;

    public PlayerActionService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public void attack(PlayerActionRequestDTO playerActionRequestDTO) {
        Player player = playerService.getPlayerById(playerActionRequestDTO);

        // Coditional for which attack will be used
        playerService.savePlayer(player);
    }
}
