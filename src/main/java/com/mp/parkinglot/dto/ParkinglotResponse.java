package com.mp.parkinglot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ParkinglotResponse {
    private String id;
    // 생각해보니 카카오에서 가져온 id랑 서울데이터광장에서 가져온 id랑 다를 수도 있는 거 아님?
    private Double empty;
    private Double total;
    private String ratio;
    private Double charge;
}
