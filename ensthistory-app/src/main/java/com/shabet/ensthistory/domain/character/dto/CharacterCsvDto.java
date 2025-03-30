package com.shabet.ensthistory.domain.character.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharacterCsvDto {

    private String charFamilyName;
    private String charGivenName;
    private String mainUnitName;
    private Integer memOrder;
    private String charAgency;
}
