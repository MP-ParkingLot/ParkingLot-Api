package com.mp.parkinglot.service;


import com.mp.parkinglot.entity.User;
import com.mp.parkinglot.repository.UserRepository;
import com.mp.parkinglot.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    private boolean authenticate(String accessToken) {
        return !jwtUtil.validateToken(accessToken).isEmpty();
    }

    private String getUserId(String accessToken) {
        if (authenticate(accessToken)) {
            Claims claim = jwtUtil.validateToken(accessToken);
            return claim.getSubject();
        }
        return null;
    }

    public User getUser(String accessToken) {
        if (getUserId(accessToken) != null) {
            return userRepository.findById(getUserId(accessToken)).get();
        }
        return null;
    }

}
