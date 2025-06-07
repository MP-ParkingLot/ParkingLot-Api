package com.mp.parkinglot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mp.parkinglot.entity.Review;
import com.mp.parkinglot.strings.PLCategories;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewRequest {
    private String title;

    private String contents;

    private Integer rate;

    private PLCategories categories;

    public Review toReview() {
        return Review.builder()
                .title(title)
                .contents(contents)
                .rate(rate)
                .bathroom(categories.getBathroom())
                .wide(categories.getWide())
                .charger(categories.getCharger())
                .build();
    }
}
