package com.battlejawn.Controllers;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Config.JsonParser;
import com.battlejawn.Service.EnemyMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.net.URI;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api/enemy-move")
public class EnemyMoveController {
    @Autowired
    private final EnemyMoveService enemyMoveService;
    private final Logger logger = Logger.getLogger(EnemyMoveController.class.getName());

    public EnemyMoveController(EnemyMoveService enemyMoveService){
        this.enemyMoveService = enemyMoveService;
    }

    @PostMapping
    public ResponseEntity<HeroMoveDTO> enemyMove(@RequestBody String data) {
        logger.info("Inside enemyMove controller method. Data: " + data + ".");
        JsonParser jsonParser;
        jsonParser = new JsonParser();
        Long parsedBattleId = jsonParser.extractBattleSessionId(data);
        HeroMoveDTO enemyMoveDTO = enemyMoveService.enemyMove(parsedBattleId);
        logger.info("Enemy Move DTO: " + enemyMoveDTO);

        if (enemyMoveDTO != null) {
            URI location = URI.create("/enemy-move-dto/");
            return ResponseEntity.created(location).body(enemyMoveDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
