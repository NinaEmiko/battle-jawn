package com.battlejawn.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerSelectionController {

    @RequestMapping("/Player-Selection")
    public String getRoles() {
        return "This is where you can choose roles!";
    }

}
