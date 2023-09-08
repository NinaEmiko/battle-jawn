package com.battlejawn.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.battlejawn.Entities.Character;
import com.battlejawn.Service.CharacterService;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/characters")
    public ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> characters = characterService.getAllCharacters();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        
        if (character != null) {
            return new ResponseEntity<>(character, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> addCharacter(@RequestBody Character newCharacter) {
        Character character = characterService.saveCharacter(newCharacter);
        if (character != null) {
            URI location = URI.create("/characters/" + character.getId());
            return ResponseEntity.created(location).build();
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteCharacterById(@PathVariable Long userId) {
        characterService.deleteCharacterById(userId);
        if (characterService.getCharacterById(userId) != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // @PostMapping("../PlayerAction/PlayerAction")
    // public void playerAction(@RequestBody InputDTO input) {
    //     playerService.move(input);
    // }

    // @PostMapping("../Player/Tank")
    // public ResponseEntity<String> createTank(@RequestBody TankDTO tankDTO) {
    //     try {
    //         playerService.createTank(tankDTO);
    //         return ResponseEntitiy.ok("Tank created successfully");
    //     } catch (Eception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Tank");
    //     }
    // }
}
