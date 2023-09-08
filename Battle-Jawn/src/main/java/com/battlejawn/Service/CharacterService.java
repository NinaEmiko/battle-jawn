package com.battlejawn.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.battlejawn.Entities.Character;
import com.battlejawn.Repository.CharacterRepository;

@Service
public class CharacterService {

    private CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    public Character getCharacterById(Long id) {
        return characterRepository.getById(id);
    }

    public Character saveCharacter(Character character){
        return characterRepository.save(character);
    }

    public void deleteCharacterById(Long id) {
        characterRepository.deleteById(id);
    }

}