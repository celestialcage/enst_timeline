package com.shabet.ensthistory.security.jwt.provider;

import com.shabet.ensthistory.member.dto.MemberDto;
import com.shabet.ensthistory.member.dto.UserAuthDto;
import com.shabet.ensthistory.member.repository.MemberRepository;
import com.shabet.ensthistory.prop.JwtProps;
import com.shabet.ensthistory.security.custom.CustomUserDetails;
import com.shabet.ensthistory.security.jwt.constants.JwtConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JWT 관련 기능 제공 클래스
 * 1. 토큰 생성
 * 2. 토큰 해석
 * 3. 토큰 유효성 검사
 */
@Slf4j
@Component
public class JwtTokenProvider {

    @Autowired
    private JwtProps jwtProps; // 시크릿 키 가져오는 빈

    // db에서 user 조회해주는 repository... 필요
    private final MemberRepository memberRepository;

    private final long accessTokenExpirationTime = 1000L * 60 * 15; // 15분
    private final long refreshTokenExpirationTime = 1000L * 60 * 60 * 24 * 7; // 7일

    public JwtTokenProvider(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String generateAccessToken(long userNo, String userId, List<String> roles) {
        return generateToken(userNo, userId, roles, accessTokenExpirationTime);
    }

    public String generateRefreshToken(long userNo, String userId, List<String> roles) {
        return generateToken(userNo, userId, roles, refreshTokenExpirationTime);
    }

    /**
     * 1. 토큰 생성 함수
     * @param userNo
     * @param userId
     * @param roles
     * @param expireTime
     * @return
     */
    public String generateToken(long userNo, String userId, List<String> roles, long expireTime) {
        byte[] signingKey = getSigningKey();

        String jwt = Jwts.builder()
                    .signWith(Keys.hmacShaKeyFor(signingKey), Jwts.SIG.HS512)
                    .header()
                    .add("typ", JwtConstants.TOKEN_TYPE)
                    .and()
                    .expiration(new Date(System.currentTimeMillis() + expireTime))
                    .claim("uno", "" + userNo)
                    .claim("uid", userId)
                    .claim("rol", roles)
                    .compact();

        log.info("Generating JWT token for user {} with role {}", userNo, roles);

        return jwt;
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String authHeader) {
        if (authHeader == null || authHeader.length() == 0)
            return null;

        byte[] signingKey = getSigningKey();

        try {
            // jwt 추출 (Bearer _ {jwt}) -> {jwt}
            String jwt = authHeader.replace(JwtConstants.TOKEN_PREFIX, "");

            // jwt 파싱
            Jws<Claims> parsedToken = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(signingKey))
                    .build()
                    .parseSignedClaims(jwt);

            log.info("Parsed JWT token: {}", parsedToken);

            // 인증된 사용자 번호
            String userNo = parsedToken.getPayload().get("uno").toString();
            long no = (userNo == null) ? 0L : Long.parseLong(userNo);
            log.info("parsedToken UserNo {}, long UserNo {}", userNo, no);

            // 인증된 사용자 아이디
            String userId = parsedToken.getPayload().get("uid").toString();
            log.info("parsedToken UserId {}", userId);

            // 인증된 사용자 권한 (여러개 아니라서... String 되려나)
            Claims claims = parsedToken.getPayload();
            Object roles = claims.get("rol");
            log.info("claims Roles {}", roles);
            
            // 토큰에 userId 있는지 확인 (없으면 진행 불가)
            if (userId == null || userId.length() == 0)
                return null;
            
            // id 있으면 MemberDto 객체 하나 만들어줌
            MemberDto memberDto = new MemberDto();
            memberDto.setUserNo(no);
            memberDto.setUserName(userId);
            // 권한 지금 list가 아니라서..(진짜 바꿔야하나 겁나 고민중..)
            List<UserAuthDto> authList = ((List<?>) roles)
                    .stream()
                    .map(auth -> new UserAuthDto(no, auth.toString()))
                    .collect(Collectors.toList());
            memberDto.setAuthList(authList);

            // CustomUser에 권한 담기
            List<SimpleGrantedAuthority> authorities = ((List<?>) roles)
                    .stream()
                    .map(auth -> new SimpleGrantedAuthority((String) auth))
                    .collect(Collectors.toList());

            // 토큰 유효하면 name, email도 담아주기 (db 조회)
            try {
                MemberDto memberInfo = memberRepository.findById(no)
                        .map(member -> {
                            List<UserAuthDto> roleList = member.getRoles().stream()
                                    .map(auth -> new UserAuthDto(
                                            auth.getAuthId(),
                                            auth.getMember().getUserNo(),
                                            auth.getRole()
                                    ))
                                    .collect(Collectors.toList());

                            return new MemberDto(
                                    member.getUserNo(),
                                    member.getUserName(),
                                    member.getName(),
                                    member.getEmail(),
                                    roleList
                            );
                        })
                        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
                if (memberInfo != null) {
                    memberDto.setName(memberInfo.getName());
                    memberDto.setEmail(memberInfo.getEmail());
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                log.error("토큰 유효 -> DB 추가 정보 조회 시 에러 발생");
            }

            UserDetails userDetails = new CustomUserDetails(memberDto); // 업캐스팅

            return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        } catch (ExpiredJwtException exception) {
            log.warn("Request to parse expired JWT : {} failed : {}", authHeader, exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            log.warn("Request to parse unsupported JWT : {} failed : {}", authHeader, exception.getMessage());
        } catch (MalformedJwtException exception) {
            log.warn("Request to parse invalid JWT : {} failed : {}", authHeader, exception.getMessage());
        } catch (IllegalArgumentException exception) {
            log.warn("Request to parse empty or null JWT : {} failed : {}", authHeader, exception.getMessage());
        }

        return null;
    }

    /**
     *
     * @param jwt
     * @return
     */
    public boolean validateToken(String jwt) {
        byte[] signingKey = getSigningKey();

        try {
            Jws<Claims> parsedToken = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(signingKey))
                    .build()
                    .parseSignedClaims(jwt);

            log.info("#### 토큰 만료 기간 ####");
            log.info("->" + parsedToken.getPayload().getExpiration());

            Date exp = parsedToken.getPayload().getExpiration();

            return !exp.before(new Date());
        } catch (ExpiredJwtException exception) {
            log.error("Token Expired"); // 토큰 만료
            return false;
        } catch (JwtException exception) {
            log.error("Token Tampered"); // 토큰 손상 (헤더나 payload가 변조. 시크릿키가 서버꺼랑 불일치하거나)
            return false;
        } catch (NullPointerException exception) {
            log.error("Token is null"); // 토큰 없음
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * key 가져오는 함수
     */
    private byte[] getSigningKey() {
        return jwtProps.getSecretKey().getBytes();
    }
}
