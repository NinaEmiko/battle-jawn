package com.battlejawn.Service;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.battlejawn.Entities.Hero.Caster;
import com.battlejawn.Entities.Hero.DPS;
import com.battlejawn.Entities.Hero.Healer;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Entities.Hero.Toon;
import com.battlejawn.Repository.ToonRepository;

@Service
public class ToonService {

    private ToonRepository toonRepository;

    public ToonService(ToonRepository toonRepository) {
        this.toonRepository = toonRepository;
    }

    public String getToonType(Long id) {
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
        return toonRepository.getById(id);
    }

    public List<Toon> getAllToons() {
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
