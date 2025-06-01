package com.shabet.ensthistory.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long userNo;
    private String userName;
    private String userPw;
    private String name;
    private String email;
    private int enabled;

    List<UserAuthDto> authList;

    public MemberDto(Long userNo, String userName, String name, String email, List<UserAuthDto> authList) {
        this.userNo = userNo;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.authList = authList;
    }
}
