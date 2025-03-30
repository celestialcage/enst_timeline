package com.shabet.ensthistory.global.utils;

import com.shabet.ensthistory.domain.character.dto.CharacterCsvDto;
import com.shabet.ensthistory.domain.unit.dto.UnitCsvDto;

import java.util.HashMap;
import java.util.Map;

public class CsvUtils {
    public static Map<String, Integer> parseHeader(String headerLine) {
        Map<String, Integer> headerMap = new HashMap<String, Integer>();
        String[] headers = headerLine.split(",", -1);
        for (int i = 0; i < headers.length; i++) {
            headerMap.put(headers[i].trim(), i);
        }
        return headerMap;
    }

    public static UnitCsvDto toUnitCsvDto(String[] tokens, Map<String, Integer> headerMap) {
        UnitCsvDto dto = new UnitCsvDto();
        dto.setUnitName(tokens[headerMap.get("unit_name")]);
        dto.setUnitAbbr(tokens[headerMap.get("unit_abbr")]);
        dto.setUnitType(tokens[headerMap.get("unit_type")]);
        dto.setUnitOrder(Integer.parseInt(tokens[headerMap.get("unit_order")]));
        dto.setUnitAgency(tokens[headerMap.get("unit_agency")]);
        dto.setUnitKorName(tokens[headerMap.get("unit_kor_name")]);
        dto.setUnitEnSearch(tokens[headerMap.get("unit_en_search")]);
        if (headerMap.containsKey("unit_jp_search")) {
            dto.setUnitJpSearch(tokens[headerMap.get("unit_jp_search")]);
        }
        dto.setUnitKorSearch(tokens[headerMap.get("unit_kor_search")]);

        return dto;
    }

    public static CharacterCsvDto toCharacterCsvDto(String[] tokens, Map<String, Integer> headerMap) {
        CharacterCsvDto dto = new CharacterCsvDto();

        System.out.println("헤더 Map: " + headerMap.keySet());

        dto.setCharFamilyName(tokens[headerMap.get("char_family_name")]);
        dto.setCharGivenName(tokens[headerMap.get("char_given_name")]);
        dto.setMainUnitName(tokens[headerMap.get("main_unit_name")]);
        dto.setCharAgency(tokens[headerMap.get("char_agency")]);

        if (headerMap.containsKey("mem_order")) {
            String orderToken = tokens[headerMap.get("mem_order")];
            dto.setMemOrder(orderToken.isBlank() ? null : Integer.parseInt(orderToken));
        }

        return dto;
    }
}
