package com.battlejawn.Controllers;

import com.battlejawn.Config.JsonParser;
import com.battlejawn.Service.StoreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/store")
@AllArgsConstructor
public class StoreController {
    private final StoreService storeService;
    private final JsonParser jsonParser;
    private final Logger logger = Logger.getLogger(StoreController.class.getName());
    @PutMapping("/buy")
    public void buy(@RequestBody @Valid String purchaseOrder) {
        logger.info("Inside buy controller method.");
        Long heroId = jsonParser.extractHeroId(purchaseOrder);
        String item = jsonParser.extractItem(purchaseOrder);
        int quantity = jsonParser.extractQuantity(purchaseOrder);
        storeService.buy(heroId, item, quantity);
    }
}
