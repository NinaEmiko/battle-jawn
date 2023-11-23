package com.battlejawn.Controllers;

import com.battlejawn.Config.HeroMoveDTO;
import com.battlejawn.Config.JsonParser;
import com.battlejawn.Config.UserResponse;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Service.HeroMoveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api/hero-move")
public class HeroMoveController {

    private final HeroMoveService heroMoveService;
    private JsonParser jsonParser;
    private final Logger logger = Logger.getLogger(HeroMoveController.class.getName());

    public HeroMoveController(HeroMoveService heroMoveService) {
        this.heroMoveService = heroMoveService;
    }

    @PostMapping
    public ResponseEntity<HeroMoveDTO> heroMove(@RequestBody String data) {
        logger.info("Inside heroMove controller method. Data: " + data + ".");
        jsonParser = new JsonParser();
        String parsedMove = jsonParser.extractMove(data);
        Long parsedBattleId = jsonParser.extractBattleSessionId(data);
        HeroMoveDTO heroMoveDTO = heroMoveService.heroMove(parsedMove, parsedBattleId);

        if (heroMoveDTO != null) {
            URI location = URI.create("/hero-move-dto/");
            return ResponseEntity.created(location).body(heroMoveDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
