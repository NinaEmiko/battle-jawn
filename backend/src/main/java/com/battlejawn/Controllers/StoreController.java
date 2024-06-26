package com.battlejawn.Controllers;

import com.battlejawn.Config.JsonParser;
import com.battlejawn.DTO.UserAccountDTO;
import com.battlejawn.Service.StoreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/store")
@AllArgsConstructor
@Validated
public class StoreController {
    private final StoreService storeService;
    private final JsonParser jsonParser;
    private final Logger logger = Logger.getLogger(StoreController.class.getName());
    @PostMapping("/buy")
    public ResponseEntity<String> buy(@RequestBody @Valid String purchaseOrder) {
        logger.info("Inside buy controller method. purchaseOrder: " + purchaseOrder);
        Long heroId = jsonParser.extractHeroId(purchaseOrder);
        String item = jsonParser.extractItem(purchaseOrder);
        int quantity = jsonParser.extractQuantity(purchaseOrder);

        String response = storeService.buy(heroId, item, quantity);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/sell")
    public ResponseEntity<String> sell(@RequestBody @Valid String sellOrder) {
        logger.info("Inside sell controller method. purchaseOrder: " + sellOrder);
        Long heroId = jsonParser.extractHeroId(sellOrder);
        String item = jsonParser.extractItem(sellOrder);
        int quantity = jsonParser.extractQuantity(sellOrder);

        String response = storeService.sell(heroId, item, quantity);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
