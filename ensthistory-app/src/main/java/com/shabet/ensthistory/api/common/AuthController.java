package com.shabet.ensthistory.api.common;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class AuthController {

//    @PostMapping("/reissue")
//    public ResponseEntity<String> reissue(HttpServletRequest request) {
//        String refreshToken = jwtCookieUtil.extractTokenFromCookie(request, "refreshToken");
//
//        return ResponseEntity.ok(new LoginResponse(newAccessToken));
//    }

//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(HttpServletRequest request) {
//        return ResponseEntity.ok().build();
//    }
}
