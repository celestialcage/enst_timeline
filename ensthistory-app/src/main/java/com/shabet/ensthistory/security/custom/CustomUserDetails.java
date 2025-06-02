package com.shabet.ensthistory.security.custom;

import com.shabet.ensthistory.member.dto.MemberDto;
import com.shabet.ensthistory.member.dto.UserAuthDto;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CustomUserDetails implements UserDetails {

    private MemberDto memberDto;

    public CustomUserDetails(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    /**
     * 권한 응답해주는 getter 메서드
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<UserAuthDto> authList = memberDto.getAuthList();

        Collection<SimpleGrantedAuthority> roleList = authList.stream()
                .map((auth) -> new SimpleGrantedAuthority(auth.getAuth()))
                .collect(Collectors.toList());

        return roleList;
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
