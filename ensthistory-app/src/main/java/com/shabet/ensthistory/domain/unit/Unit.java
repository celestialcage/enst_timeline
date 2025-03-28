package com.shabet.ensthistory.domain.unit;

import com.shabet.ensthistory.domain.common.enums.Agency;
import com.shabet.ensthistory.domain.unit.dto.UnitCsvDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Optional;

@Getter
@Entity
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Integer id;

    @Column(name = "unit_name", length = 50, nullable = false)
    private String name;

    @Column(name = "unit_abbr", length = 8, nullable = false)
    private String abbr;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit_type", nullable = false)
    private UnitType type;

    @Column(name = "unit_order")
    private Integer unitOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit_agency", nullable = false)
    private Agency agency;

    @Column(name = "unit_kor_name", length = 20)
    private String korName;

    @Column(name = "unit_en_search", length = 50)
    private String enSearch;

    @Column(name = "unit_jp_search", length = 50)
    private String jpSearch;

    @Column(name = "unit_kor_search", length = 20)
    private String korSearch;

    // 생성자
    protected Unit() {} // JPA 기본 생성자

    public Unit(String name, String abbr, UnitType type, Integer unitOrder, Agency agency,
                String korName, String enSearch, String jpSearch, String korSearch) {
        this.name = name;
        this.abbr = abbr;
        this.type = type;
        this.unitOrder = unitOrder;
        this.agency = agency;
        this.korName = korName;
        this.enSearch = enSearch;
        this.jpSearch = jpSearch;
        this.korSearch = korSearch;
    }

    public Unit(UnitCsvDto dto) {
        this.name = dto.getUnitName().trim();
        this.abbr = dto.getUnitAbbr().trim();
        this.type = UnitType.valueOf(dto.getUnitType().trim().toUpperCase());
        this.unitOrder = dto.getUnitOrder();
        this.agency = Agency.valueOf(dto.getUnitAgency().trim().toUpperCase());
        this.korName = dto.getUnitKorName().trim();
        this.enSearch = Optional.ofNullable(dto.getUnitEnSearch()).orElse("").trim();
        this.jpSearch = Optional.ofNullable(dto.getUnitJpSearch()).orElse("").trim();
        this.korSearch = Optional.ofNullable(dto.getUnitKorSearch()).orElse("").trim();
    }
}
