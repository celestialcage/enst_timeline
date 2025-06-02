package com.shabet.ensthistory.api.common;

import com.shabet.ensthistory.domain.character.dto.CharacterIdNameDto;
import com.shabet.ensthistory.service.character.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/common/character")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/list")
    public List<CharacterIdNameDto> getCharactersForAutocomplete() {

        List<CharacterIdNameDto> characterNames = characterService.getCharacterIdGivenNameList();

        return characterNames;
    }
}
