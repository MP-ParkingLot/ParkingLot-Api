package com.mp.parkinglot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkinglotResult {
    public Long list_total_count;
    public ApiResultCode RESULT;
    public List<ParkinglotApiResponse> row;
}
