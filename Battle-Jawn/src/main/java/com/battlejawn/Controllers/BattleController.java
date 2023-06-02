package com.battlejawn.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BattleController {
    
    @RequestMapping("/Battle")
    public String battle() {
        return "This is where the player battles!";
    }
}
