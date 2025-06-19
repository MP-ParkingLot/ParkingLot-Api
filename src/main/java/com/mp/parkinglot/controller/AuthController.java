package com.mp.parkinglot.controller;

import com.mp.parkinglot.dto.AuthRequest;
import com.mp.parkinglot.entity.User;
import com.mp.parkinglot.repository.UserRepository;
import com.mp.parkinglot.strings.JwtRole;
import com.mp.parkinglot.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) {
        log.info("Login request received");
        String id = authRequest.getId();
        String password = authRequest.getPassword();

        Optional<User> existing = userRepository.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "user not found"));
        }

        if (!password.equals(existing.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "wrong password"));
        }

        String accessToken = jwtUtil.generateAccessToken(id, JwtRole.ROLE_USER.getRole());

        ResponseCookie cookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)        // JS 접근 방지 (보안상 중요)
                .secure(false)         // HTTPS 환경이라면 true
                .path("/")             // 전체 경로에 적용
                .sameSite("Lax")       // 또는 "Strict", "None"
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of("message", "sign-in success"));
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody AuthRequest authRequest) {
        log.info("Signup request received");
        String id = authRequest.getId();
        String name = authRequest.getNickname();
        String password = authRequest.getPassword();

        Optional<User> existing = userRepository.findById(id);
        if (existing.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "id is used"));
        }

        User user = new User(id, name, password);
        User saved = userRepository.save(user);

        String accessToken = jwtUtil.generateAccessToken(id, JwtRole.ROLE_USER.getRole());

        ResponseCookie cookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)        // JS 접근 방지 (보안상 중요)
                .secure(false)         // HTTPS 환경이라면 true
                .path("/")             // 전체 경로에 적용
                .sameSite("Lax")       // 또는 "Strict", "None"
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of("message", saved.getId() + " created"));
    }
}
