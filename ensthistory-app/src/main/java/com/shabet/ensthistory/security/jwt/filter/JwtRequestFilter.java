package com.shabet.ensthistory.security.jwt.filter;

import com.shabet.ensthistory.security.jwt.constants.JwtConstants;
import com.shabet.ensthistory.security.jwt.provider.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtRequestFilter(final JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


    /**
     * jwt 요청 필터. JWT 토큰 유효성 검사
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 헤더에서 jwt 토큰을 가져옴
        String header = request.getHeader(JwtConstants.TOKEN_HEADER);
        log.info("Authorization: " + header);

        // Bearer + {jwt} 체크
        if (header == null || header.length() == 0 || !header.startsWith(JwtConstants.TOKEN_PREFIX)) {
            // 토큰 없으면 바로 다음 필터로 넘어감
            filterChain.doFilter(request, response);
            return;
        }

        // JWT 있으면
        String jwt = header.replace(JwtConstants.TOKEN_PREFIX, "");

        // 토큰 유효성 검사
        if (jwtTokenProvider.validateToken(jwt)) {
            log.info("[req filter] 유효한 JWT 토큰입니다.");

            // 토큰 해석
            Authentication authentication = jwtTokenProvider.getAuthentication(jwt);

            // 로그인
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 다음 필터 (지금은 안 쓰지만 추가로 나중에 확장된다면 다음 필터로 넘기면 됨.)
        filterChain.doFilter(request, response);
    }
}
