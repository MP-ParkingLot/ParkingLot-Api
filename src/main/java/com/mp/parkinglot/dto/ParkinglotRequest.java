package com.mp.parkinglot.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ParkinglotRequest {
    private List<String> parkingLot;        // 주차장 주소
}
