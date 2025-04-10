package com.shabet.ensthistory.domain.turn.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TurnGridRequestDto {
    private int turnSeq; // 표시할 턴 순서. id와 달리 수정 가능.
    private int gameYear; // 몇 년차 이벤트인지 확인할 수 있는 필드. year_season 테이블의 pk이자 의미 있는 데이터.
    private String eventType;
    private String eventTitle;
    private String eventChar;
    private String eventCardAttr; // 메인캐릭터 이벤트카드의 속성1
    private String eventCardProp; // 메인캐릭터 이벤트카드의 속성2
    private String themeScout;
    private String tScoutChar;
    private String tScoutCardAttr;
    private String tScoutCardProp;
    private String featureScout;
    private String fScoutChar;
    private String fScoutCardAttr;
    private String fScoutCardProp;
    private String chScout;
    private String chScoutChar;
    private String chScoutCardAttr;
    private String chScoutCardProp;
    private String chIrregular;
    private String chIrrChar;
    private String chIrrCardAttr;
    private String chIrrCardProp;
}
