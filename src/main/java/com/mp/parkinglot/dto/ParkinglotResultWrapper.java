package com.mp.parkinglot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ParkinglotResultWrapper {
    @JsonProperty("GetParkingInfo")
    public ParkinglotResult getParkingInfo;
}
