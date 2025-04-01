package com.shabet.ensthistory.domain.turn.dto;

import java.util.List;

public class TurnInfoResponseDto {
    private int turnSeq; // 표시할 턴 순서. id와 달리 수정 가능.
    private int gameYear; // 몇 년차 이벤트인지 확인할 수 있는 필드. year_season 테이블의 pk이자 의미 있는 데이터.
    private String eventTitle;
    private List<TurnRowDto> rows; // cell merge 여지가 있어서 ㅠㅠㅠㅠㅠㅠ
    private String themeScout;
    private String featureScout;
    private String chScout;
    private String chIrregular;

    // 이녀석들의 처분은 생각해보아야...
//    private String mainEventChar;
//    private String mainEventCardAttr; // 메인캐릭터 이벤트카드의 속성1
//    private String mainEventCardProp; // 메인캐릭터 이벤트카드의 속성2
//    private String subEventChar;
//    private String subEventCardAttr;
//    private String subEventCardProp;
//    private String tScoutChar;
//    private String tScoutCardAttr;
//    private String tScoutCardProp;
//    private String fScoutChar;
//    private String fScoutCardAttr;
//    private String fScoutCardProp;
    private String chScoutChar;
    private String chScoutCardAttr;
    private String chScoutCardProp;
    private String chIrrChar;
    private String chIrrCardAttr;
    private String chIrrCardProp;
}
