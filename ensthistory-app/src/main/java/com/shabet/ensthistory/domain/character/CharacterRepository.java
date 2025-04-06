package com.shabet.ensthistory.domain.character;

import com.shabet.ensthistory.domain.character.dto.CharacterIdNameDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {

    @Query("SELECT new com.shabet.ensthistory.domain.character.dto.CharacterIdNameDto(c.id, c.givenName) FROM Character c")
    List<CharacterIdNameDto> findCharactersIdAndGivenNameList();

}
