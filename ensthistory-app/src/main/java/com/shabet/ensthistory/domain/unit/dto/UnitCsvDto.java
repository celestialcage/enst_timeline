package com.shabet.ensthistory.domain.unit.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UnitCsvDto {
    private String unitName;
    private String unitAbbr;
    private String unitType;
    private Integer unitOrder;
    private String unitAgency;
    private String unitKorName;
    private String unitEnSearch;
    private String unitJpSearch;
    private String unitKorSearch;
}
