package com.mp.parkinglot.dto;

import com.mp.parkinglot.entity.Review;
import lombok.Getter;
import java.util.Map;

@Getter
public class ReviewRequest {
    private String title;

    private String contents;

    private Integer rate;

    private Map<String, Boolean> categories;

    public Review toReview() {
        return Review.builder()
                .title(title)
                .contents(contents)
                .rate(rate)
                .bathroom(categories.get("화장실"))
                .wide(categories.get("넓은공간"))
                .charger(categories.get("충전소"))
                .build();
    }
}
