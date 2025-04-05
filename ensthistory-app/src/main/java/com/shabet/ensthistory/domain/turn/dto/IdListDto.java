package com.shabet.ensthistory.domain.turn.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class IdListDto {
    private List<Integer> ids;
    private LocalDateTime deleteDateTime;
}
