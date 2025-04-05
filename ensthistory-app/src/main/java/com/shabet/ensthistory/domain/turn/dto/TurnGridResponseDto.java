package com.shabet.ensthistory.domain.turn.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TurnGridResponseDto {
    private int turnSeq; // 표시할 턴 순서. id와 달리 수정 가능.
    private int gameYear; // 몇 년차 이벤트인지 확인할 수 있는 필드. year_season 테이블의 pk이자 의미 있는 데이터.
    private String eventType;
    private String eventTitle;
    private List<TurnResponseRowDto> rows; // cell merge 여지가 있어서 ㅠㅠㅠㅠㅠㅠ
    private String themeScout;
    private String featureScout;
    private String chScout;
    private String chIrregular;
    private String chScoutChar;
    private String chScoutCardAttr;
    private String chScoutCardProp;
    private String chIrrChar;
    private String chIrrCardAttr;
    private String chIrrCardProp;
}
