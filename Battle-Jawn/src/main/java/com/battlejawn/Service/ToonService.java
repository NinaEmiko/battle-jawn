package com.battlejawn.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.persistence.DiscriminatorValue;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.battlejawn.Controllers.ToonController;
import com.battlejawn.Entities.Hero.Caster;
import com.battlejawn.Entities.Hero.DPS;
import com.battlejawn.Entities.Hero.Healer;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Entities.Hero.Toon;
import com.battlejawn.Repository.ToonRepository;

@Service
public class ToonService {

    private ToonRepository toonRepository;
    private Logger logger = Logger.getLogger(ToonController.class.getName());

    public ToonService(ToonRepository toonRepository) {
        this.toonRepository = toonRepository;
    }

    public String getToonType(Long id) {
        logger.info("Inside getToonType Service method");
        Toon toon = toonRepository.findById(id).orElse(null);

        if (toon != null) {
            DiscriminatorValue discriminatorValue = toon.getClass().getAnnotation(DiscriminatorValue.class);
            if (discriminatorValue != null) {
                return discriminatorValue.value();
            } return null;
        }
        return null;
    }
    
    public Toon getToonById(Long id){
        logger.info("Inside Toon Service ID: " + id);
        Optional<Toon> toon = toonRepository.findById(id);
        if (toon.isPresent()) {
            logger.info("Inside Toon isPresent");
            return toon.get();
        } else {
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }
    }

    public List<Toon> getAllToons() {
        logger.info("Inside getAllToons Service method");
        return toonRepository.findAll();
    }

    @Transactional
    public Toon saveToon(String role) {
        try {
            Toon toon = new Healer();

            switch (role) {
                case "Tank": 
                                toon = new Tank();
                                break;
                case "DPS": 
                                toon = new DPS();
                                break;
                case "Caster": 
                                toon = new Caster();
                                break;
            }

            toonRepository.save(toon);
            return toon;
        } catch(Exception e) {
            throw new RuntimeException("Failed to save toon: " + e.getMessage());
        }
    }
 }
