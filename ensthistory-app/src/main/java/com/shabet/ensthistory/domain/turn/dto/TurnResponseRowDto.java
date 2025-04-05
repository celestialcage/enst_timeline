package com.shabet.ensthistory.domain.turn.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TurnResponseRowDto {
    private Integer order;
    private String eventChar;
    private String eventCardAttr;
    private String eventCardProp;
    private String tScoutChar;
    private String tScoutCardAttr;
    private String tScoutCardProp;
    private String fScoutChar;
    private String fScoutCardAttr;
    private String fScoutCardProp;
}
