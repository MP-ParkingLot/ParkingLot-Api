package com.mp.parkinglot.dto;

import lombok.Getter;

@Getter
public class LikeRequest {
    private boolean isLike;

    public boolean isLike() {
        return isLike;
    }

    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }
}

