package com.mp.parkinglot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkinglotResult {
    @JsonProperty("list_total_count")
    public Long listTotalCount;

    @JsonProperty("RESULT")
    public ApiResultCode result;

    @JsonProperty("row")
    public List<ParkinglotApiResponse> row;
}
