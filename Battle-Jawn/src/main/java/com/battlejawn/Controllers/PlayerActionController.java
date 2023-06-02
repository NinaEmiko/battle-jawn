package com.battlejawn.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PlayerActionController {

    // private final PlayerActionService playerActionService;

    // public PlayerActionController(PlayerActionService playerActionService) {
    // this.playerActionService = playerActionService;
    // } that

    // @PostMapping("/attack")
    // public ResponseEntity<String> attack(@RequestBody PlayerActionDTO
    // playerActionDTO) {
    // try {
    // playerActionService.attack(playerActionRequestDTO);
    // return ResponseEntity.ok("Attack successful");
    // } catch (Exception e) {
    // return ResponseEntity.badRequest().body("Failed to perform attack: " +
    // e.getMessage());
    // }
    // }

    // @PostMapping("/heal")
    // public ResponseEntity<String> heal(@RequestBody PlayerActionDTO
    // playerActionDTO) {
    // try {
    // playerActionService.heal(playerActionRequestDTO);
    // return ResponseEntity.ok("Heal successful");
    // } catch (Exception e) {
    // return ResponseEntity.badRequest().body("Failed to perform heal: " +
    // e.getMessage());
    // }
    // }

    // @PostMapping("/run")
    // public ResponseEntity<String> run(@RequestBody PlayerActionDTO
    // playerActionDTO) {
    // try {
    // playerActionService.run(playerActionRequestDTO);
    // return ResponseEntity.ok("Run successful");
    // } catch (Exception e) {
    // return ResponseEntity.badRequest().body("Failed to run: " + e.getMessage());
    // }
    // }

}
