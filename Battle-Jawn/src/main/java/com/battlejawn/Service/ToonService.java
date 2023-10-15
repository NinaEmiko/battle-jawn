package com.battlejawn.Service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.battlejawn.Entities.Roles.Toon;
import com.battlejawn.Repository.ToonRepository;

@Service
public class ToonService {

    private ToonRepository toonRepository;

    public ToonService(ToonRepository toonRepository) {
        this.toonRepository = toonRepository;
    }
    
    public Toon getToonById(Long id){
        return toonRepository.getById(id);
    }

    public List<Toon> getAllToons() {
        return toonRepository.findAll();
    }

    @Transactional
    public Toon saveToon(String role) {
        try {
            Toon toon = new Toon();

            switch (role) {
                case "Tank": 
                                toon.setHealth(120);
                                toon.setMaxHealth(120);
                                toon.setPotions(3);
                                toon.setMaxPotions(3);
                                toon.setRole("Tank");
                                break;
                case "DPS": 
                                toon.setHealth(90);
                                toon.setMaxHealth(90);
                                toon.setPotions(2);
                                toon.setMaxPotions(3);
                                toon.setRole("DPS");
                                break;
                case "Caster": 
                                toon.setHealth(90);
                                toon.setMaxHealth(90);
                                toon.setPotions(3);
                                toon.setMaxPotions(3);
                                toon.setRole("Caster");
                                break;
                case "Healer": 
                                toon.setHealth(100);
                                toon.setMaxHealth(100);
                                toon.setPotions(0);
                                toon.setMaxPotions(0);
                                toon.setRole("Healer");
                                break;
            }
            toonRepository.save(toon);
            return toon;
        } catch(Exception e) {
            throw new RuntimeException("Failed to save toon: " + e.getMessage());
        }
    }
 }
