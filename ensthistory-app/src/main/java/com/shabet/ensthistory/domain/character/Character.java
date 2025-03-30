package com.shabet.ensthistory.domain.character;

import com.shabet.ensthistory.domain.character.dto.CharacterCsvDto;
import com.shabet.ensthistory.domain.common.enums.Agency;
import com.shabet.ensthistory.domain.unit.Unit;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "idol_character")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "char_id")
    private Integer id;

    @Column(name = "char_name", insertable = false, updatable = false)
    private String name;

    @Column(name = "char_family_name", length = 10, nullable = false)
    private String familyName;

    @Column(name = "char_given_name", length = 10, nullable = false)
    private String givenName;

    @Enumerated(EnumType.STRING)
    @Column(name = "char_agency", nullable = false)
    private Agency agency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_unit_id", nullable = false)
    private Unit mainUnit;

    @Column(name = "char_birth_month")
    private Integer birthMonth;

    @Column(name = "char_birth_day")
    private Integer birthDay;

    @Column(name = "launch_age")
    private Integer launchAge;

    @Column(name = "launch_season")
    private Integer launchSeason;

    @Column(name = "char_en_search", length = 50)
    private String enSearch;

    @Column(name = "char_jp_search", length = 50)
    private String jpSearch;

    // 생성자
    protected Character() {} // JPA 기본 생성자

    public Character(String familyName, String givenName, Agency agency, Unit mainUnit,
                     Integer birthMonth, Integer birthDay, Integer launchAge,
                     Integer launchSeason, String enSearch, String jpSearch) {
        this.familyName = familyName;
        this.givenName = givenName;
        this.agency = agency;
        this.mainUnit = mainUnit;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.launchAge = launchAge;
        this.launchSeason = launchSeason;
        this.enSearch = enSearch;
        this.jpSearch = jpSearch;
    }

    public Character(CharacterCsvDto dto, Unit unit) {
        this.familyName = dto.getCharFamilyName();
        this.givenName = dto.getCharGivenName();
        this.agency = Agency.valueOf(dto.getCharAgency()); // 문자열이면 enum으로 변환
        this.mainUnit = unit;
    }

}
