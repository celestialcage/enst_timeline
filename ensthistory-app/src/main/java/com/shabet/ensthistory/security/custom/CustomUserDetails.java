package com.shabet.ensthistory.security.custom;

import com.shabet.ensthistory.member.dto.MemberDto;
import com.shabet.ensthistory.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {

    private final MemberDto memberDto;

    public CustomUserDetails(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + memberDto.getUserRole().name()));
    }

    @Override
    public String getPassword() {
        return memberDto.getUserPw();
    }

    @Override
    public String getUsername() {
        return memberDto.getUserName(); // 로그인에 사용할 아이디
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // 추후 확장 가능
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // 추후 확장 가능
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // 추후 확장 가능
    }

    @Override
    public boolean isEnabled() {
        return memberDto.getEnabled() == 0 ? false : true;  // 바로 연동됨!
    }
}
