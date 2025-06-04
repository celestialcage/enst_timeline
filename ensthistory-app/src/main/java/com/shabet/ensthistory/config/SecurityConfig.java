package com.shabet.ensthistory.config;

import com.shabet.ensthistory.security.custom.CustomUserDetailsService;
import com.shabet.ensthistory.security.jwt.filter.JwtAuthenticationFilter;
import com.shabet.ensthistory.security.jwt.provider.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("Security Filter Chain ... (시큐리티 설정)");

        // 폼 기반 로그인 비활성화
        http.formLogin(login -> login.disable());

        // HTTP 기본 인증 비활성화
        http.httpBasic(basic -> basic.disable());

        // CSRF(Cross-Site Request Forgery) 공격 방어 기능 비활성화
        http.csrf(csrf -> csrf.disable());

//        // 세션 관리 정책 설정(왜 빠졌지..?)
//        // 세션 인증을 사용하지 않고 JWT를 사용하여 인증하기 때문에 불필요
//        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // TODO: 필터 설정 (필터 쓰고 와서 쓰자)
        // http.addFilterAt(new JwtAuthenticationFilter())

        // 인가 설정
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                // 서버 측 정적 자원 허용(필요시)
                // .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // 로그인, 회원가입 등 인증 없이 접근 가능한 API
                .requestMatchers("/api/auth/login", "/api/auth/register", "/api/auth/refresh").permitAll()
                // 사용자 공개 API (예: 공개된 게시글 보기)
                .requestMatchers("/api/public/**", "/api/common/**").permitAll()
                // 회원 전용 개인화 API (예: 마이페이지, 나의 포스트)
                .requestMatchers("/users/**").hasRole("USER")
                // 관리자 API
                .requestMatchers("/content-admin/**").hasRole("CONTENT_ADMIN")
                .requestMatchers("/system-admin/**").hasRole("SYSTEM_ADMIN")
                // 나머지 /api는 인증만 필요 (권한은 무관)
                .requestMatchers("/api/**").authenticated()
                // 그 외는 기본 차단
                .anyRequest().denyAll()
        );

        // 인증 방식 설정
        http.userDetailsService(customUserDetailsService);

        // 기존에 로그인 없이 했던 거
//        http
//            .csrf().disable() // CSRF 끄기
//            .authorizeHttpRequests(auth -> auth
//                    .anyRequest().permitAll() // 모든 요청 허용
//            );
        return http.build();
    }

    // PasswordEncoder 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // JwtAuthenticationFilter에서 쓰기 위해 bean 등록
    private AuthenticationManager authenticationManager;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
        return authenticationManager;
    }
}
