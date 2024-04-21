package com.battlejawn.Controllers;

import com.battlejawn.Service.LootService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/loot")
@AllArgsConstructor
public class LootController {
    private final LootService lootService;
    @GetMapping("/{id}")
    public List<String> getLoot(@PathVariable Long id) {
        return lootService.getLoot(id);
    }
}
