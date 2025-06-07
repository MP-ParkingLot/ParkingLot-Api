package com.mp.parkinglot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ApiResultCode {
    @JsonProperty("CODE")
    private String CODE;

    @JsonProperty("MESSAGE")
    private String MESSAGE;
}
