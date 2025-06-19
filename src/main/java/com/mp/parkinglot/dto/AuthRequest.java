package com.mp.parkinglot.dto;

import lombok.Getter;

@Getter
public class AuthRequest {
    private String id;
    private String nickname;
    private String password;
}
