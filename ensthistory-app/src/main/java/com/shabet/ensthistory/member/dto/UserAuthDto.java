package com.shabet.ensthistory.member.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDto {

    private Long authId;
    private Long userNo;
    private String auth;

    public UserAuthDto(Long userNo, String auth) {
        this.userNo = userNo;
        this.auth = auth;
    }
}
