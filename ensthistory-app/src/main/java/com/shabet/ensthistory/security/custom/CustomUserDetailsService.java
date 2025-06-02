package com.shabet.ensthistory.security.custom;

import com.shabet.ensthistory.member.dto.MemberDto;
import com.shabet.ensthistory.member.dto.UserAuthDto;
import com.shabet.ensthistory.member.entity.Member;
import com.shabet.ensthistory.member.repository.MemberRepository;
import com.shabet.ensthistory.prop.JwtProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JwtProps jwtProps;

    private final MemberRepository memberRepository;

    CustomUserDetailsService(JwtProps jwtProps, MemberRepository memberRepository) {
        this.jwtProps = jwtProps;
        this.memberRepository = memberRepository;
    }

    /**
     * username으로 사용자 정보를 읽어 오는 메소드
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("login - loadUserByUsername: " + username);

        MemberDto memberDto = memberRepository.findByUserName(username)
                .map(member -> {
                    List<UserAuthDto> authList = member.getRoles().stream()
                            .map(auth -> new UserAuthDto(
                                    auth.getAuthId(),
                                    member.getUserNo(), // auth.getMember().getUserNo()도 가능
                                    auth.getRole()
                            ))
                            .collect(Collectors.toList());

                        return new MemberDto(
                        member.getUserNo(),
                        member.getUserName(),
                        member.getName(),
                        member.getEmail(),
                        authList
                        );
                })
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. " + username));

        // 예외로 안 빠졌으면 사용자 있는 것.
        log.info("user(memberDto): " + memberDto.toString());

        CustomUserDetails customUserDetails = new CustomUserDetails(memberDto);

        log.info("customUserDetails(memberDto): " + customUserDetails.toString());

        return customUserDetails;
    }
}
