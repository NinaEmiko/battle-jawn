package com.battlejawn.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.battlejawn.Entities.Character;

import com.battlejawn.Service.CharacterService;

@RestController
public class CharacterController {

    private CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/characters")
    public List<Character> getAllCharacters() {
        return characterService.getAllCharacters();
    }

    @GetMapping("/id")
    public Character findCharacterById(Long id) {
        return characterService.getCharacterById(id);
    }

    @PostMapping
    public void addCharacter(Character character) {
        characterService.saveCharacter(character);
    }

    @DeleteMapping
    public void deleteCharacterById(Long id) {
        characterService.deleteCharacterById(id);
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
