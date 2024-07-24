package com.battlejawn.Controllers;

import com.battlejawn.Config.JsonParser;
import com.battlejawn.Service.TalentTreeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

@RestController
@Validated
@RequestMapping("/api/talent")
public class TalentTreeController {

    @Autowired
    private TalentTreeService talentTreeService;
    private final JsonParser jsonParser;
    private final Logger logger = Logger.getLogger(TalentTreeController.class.getName());
    public TalentTreeController(TalentTreeService talentTreeService, JsonParser jsonParser){
        this.talentTreeService = talentTreeService;
        this.jsonParser = jsonParser;
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activateTalent(@Valid @RequestBody String data){
        logger.info("Inside activateTalent controller method. Object: " + data + ".");
        Long parsedHeroId = jsonParser.extractHeroId(data);
        String parsedTalent = jsonParser.extractTalent(data);
        logger.info("Parsed Hero Id: " + parsedHeroId + ". Parsed talent: " + parsedTalent + ".");
        String response = talentTreeService.activateTalent(parsedHeroId, parsedTalent);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
