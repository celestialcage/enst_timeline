package com.shabet.ensthistory.api.common;

import com.shabet.ensthistory.member.entity.Member;
import com.shabet.ensthistory.member.repository.MemberRepository;
import com.shabet.ensthistory.security.jwt.constants.JwtConstants;
import com.shabet.ensthistory.security.jwt.provider.JwtTokenProvider;
import com.shabet.ensthistory.security.util.JwtCookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtCookieUtil jwtCookieUtil;
    private final MemberRepository memberRepository;

    public AuthController(JwtTokenProvider jwtTokenProvider, JwtCookieUtil jwtCookieUtil, MemberRepository memberRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtCookieUtil = jwtCookieUtil;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request, HttpServletResponse response) {
        // TODO: refresh token 컨트롤러 메소드 작성
        // 1. 쿠키에서 refreshToken 추출
        String refreshToken = jwtCookieUtil.extractRefreshTokenFromCookie(request);

        if (!jwtTokenProvider.validateToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 리프레시 토큰");
        }

        // 2. refreshToken에서 정보 파싱
        Long userNo = jwtTokenProvider.extractUserNoFromToken(refreshToken);
        String userId = jwtTokenProvider.extractUserIdFromToken(refreshToken);
        List<String> roles = jwtTokenProvider.extractRolesFromToken(refreshToken);

        // (선택) 3. DB에 저장된 refreshToken과 비교
        Optional<Member> memberOptional = memberRepository.findById(userNo);
        if (memberOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("존재하지 않는 사용자");
        }

        Member member = memberOptional.get();
        if (!refreshToken.equals(member.getRefreshToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 위조");
        }

        // 4. 새 accessToken, 새 refreshToken 발급
        String newAccessToken = jwtTokenProvider.generateAccessToken(userNo, userId, roles);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(userNo, userId, roles);

        // 5. 새 refreshToken → DB에 저장
        member.setRefreshToken(newRefreshToken);
        memberRepository.save(member);

        // 6. 새 refreshToken → 쿠키로 내려보냄
        jwtCookieUtil.addRefreshTokenToCookie(response, newRefreshToken);

        // body에 넣을 경우 header에 안 넣어도 됨. (json 구조에선 이게 깔끔하고 보안도 좋다고 한다)
        // response.addHeader(JwtConstants.TOKEN_HEADER, JwtConstants.TOKEN_PREFIX + newAccessToken);

        // 7. 새 accessToken → JSON으로 응답 (프론트가 localStorage에 저장)
        return ResponseEntity.ok().body(Map.of("accessToken", newAccessToken));
    }

//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(HttpServletRequest request) {
//        return ResponseEntity.ok().build();
//    }
}
