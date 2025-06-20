package com.mp.parkinglot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Setter
@Getter
public class ReviewResponse {
    private Long id;

    private String title;

    private String contents;

    private String userId;

    private Integer rate;

    private Integer likes;

    private LocalDateTime createdAt;

    private Map<String, Boolean> categories;

    private Boolean isLikedByMe = false;
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
