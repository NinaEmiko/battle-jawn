package com.battlejawn.Controllers;

import com.battlejawn.DTO.EnemyMoveDTO;
import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Config.JsonParser;
import com.battlejawn.Service.EnemyMoveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.net.URI;
import java.util.logging.Logger;

@Controller
@Validated
@RequestMapping("/api/enemy-move")
public class EnemyMoveController {
    @Autowired
    private final EnemyMoveService enemyMoveService;
    private final JsonParser jsonParser;
    private final Logger logger = Logger.getLogger(EnemyMoveController.class.getName());

    public EnemyMoveController(EnemyMoveService enemyMoveService, JsonParser jsonParser){
        this.enemyMoveService = enemyMoveService;
        this.jsonParser = jsonParser;
    }

    @PostMapping
    public ResponseEntity<EnemyMoveDTO> enemyMove(@Valid @RequestBody String data) {
        logger.info("Inside enemyMove controller method. Data: " + data + ".");
        Long parsedBattleId = jsonParser.extractBattleSessionId(data);
        EnemyMoveDTO enemyMoveDTO = enemyMoveService.enemyMove(parsedBattleId);
        logger.info("Enemy Move DTO: " + enemyMoveDTO);

        if (enemyMoveDTO != null) {
            URI location = URI.create("/enemy-move-dto/");
            return ResponseEntity.created(location).body(enemyMoveDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
