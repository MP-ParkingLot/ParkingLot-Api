package com.mp.parkinglot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mp.parkinglot.entity.Review;
import com.mp.parkinglot.strings.PLCategories;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponse {
    private Long id;

    private String title;

    private String contents;

    private Integer rate;

    private Integer likes;

    private LocalDateTime createdAt;

    private PLCategories categories;
//
//    public Review toReview() {
//        return Review.builder()
//                .title(title)
//                .contents(contents)
//                .rate(rate)
//                .likes(likes)
//                .createdAt(createdAt)
//                .bathroom(categories.getBathroom())
//                .wide(categories.getWide())
//                .charger(categories.getCharger())
//                .build();
//    }
}
