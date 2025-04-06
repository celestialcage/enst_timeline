package com.shabet.ensthistory.service.character;

import com.shabet.ensthistory.domain.character.CharacterRepository;
import com.shabet.ensthistory.domain.character.dto.CharacterIdNameDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<CharacterIdNameDto> getCharacterIdGivenNameList() {

        List<CharacterIdNameDto> characterNames = characterRepository.findCharactersIdAndGivenNameList();

        return characterNames;
    }
}
