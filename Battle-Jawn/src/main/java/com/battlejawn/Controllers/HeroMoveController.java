package com.battlejawn.Controllers;

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
    @ResponseStatus(value = HttpStatus.OK)
    public void heroMove(@RequestBody String move, String battleSessionId) {
        logger.info("Inside heroMove Controller method. Move: " + move + " Battle Session ID: " + battleSessionId);
        jsonParser = new JsonParser();
        String parsedMove = jsonParser.extractMove(move);
        Long parsedBattleId = jsonParser.extractBattleSessionId(battleSessionId);
        heroMoveService.heroMove(parsedMove, parsedBattleId);

    }
}
