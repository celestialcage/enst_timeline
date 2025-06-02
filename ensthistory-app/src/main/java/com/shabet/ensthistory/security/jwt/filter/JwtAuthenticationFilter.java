package com.shabet.ensthistory.security.jwt.filter;

import com.shabet.ensthistory.security.custom.CustomUserDetails;
import com.shabet.ensthistory.security.jwt.constants.JwtConstants;
import com.shabet.ensthistory.security.jwt.provider.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;

        // 생성자에서 필터 url 경로 설정을..
        setFilterProcessesUrl(JwtConstants.AUTH_LOGIN_URL);
    }

    /**
     * 인증 시도 메소드
     * AUTH_LOGIN_URL 경로로 요청하면, 필터로 걸러서 인증을 시도
     * @param request from which to extract parameters and perform the authentication
     * @param response the response, which may be needed if the implementation has to do a
     * redirect as part of a multi-stage authentication process (such as OIDC).
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String username = request.getParameter("username"); // id
        String password = request.getParameter("password"); // pw

        log.info("username : " + username);
        log.info("password : " + password);

        // 사용자 인증 정보 객체 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        // 사용자 인증 (로그인)
        authentication = authenticationManager.authenticate(authentication);

        // 로그인 후 authentication에 담기 정보 확인
        log.info("인증 여부: " + authentication.isAuthenticated());

        // 인증 실패 시 (username, password 불일치)
        if (!authentication.isAuthenticated()) {
            log.info("인증 실패 - 아이디 또는 비밀번호가 일치하지 않습니다.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return authentication;
    }

    /**
     * 인증 성공 메소드. JWT를 생성하고 응답 헤더에 설정함
     * @param request
     * @param response
     * @param chain
     * @param authentication the object returned from the <tt>attemptAuthentication</tt>
     * method.
     * @throws IOException
     * @throws ServletException
     */
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain
            , Authentication authentication) throws IOException, ServletException {
        log.info("인증 성공 ⭕");

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        long userNo = userDetails.getMemberDto().getUserNo();
        String userName = userDetails.getMemberDto().getUserName();

        List<String> roles = userDetails.getMemberDto().getAuthList().stream()
                .map((auth) -> auth.getAuth())
                .collect(Collectors.toList());

        // JWT
        String jwt = jwtTokenProvider.generateAccessToken(userNo, userName, roles);

        // { Authentication: Bearer {jwt} }
        response.addHeader(JwtConstants.TOKEN_HEADER, JwtConstants.TOKEN_PREFIX + jwt);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
