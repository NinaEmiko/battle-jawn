package com.battlejawn.Controllers;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Config.JsonParser;
import com.battlejawn.Service.HeroMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api/hero-move")
public class HeroMoveController {
    @Autowired
    private final HeroMoveService heroMoveService;
    private final JsonParser jsonParser;
    private final Logger logger = Logger.getLogger(HeroMoveController.class.getName());

    public HeroMoveController(HeroMoveService heroMoveService, JsonParser jsonParser) {
        this.heroMoveService = heroMoveService;
        this.jsonParser = jsonParser;
    }

    @PostMapping
    public ResponseEntity<HeroMoveDTO> heroMove(@RequestBody String data) {
        logger.info("Inside heroMove controller method. Data: " + data + ".");
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
