package com.mp.parkinglot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class ParkinglotApiResponse {
    @JsonProperty("PKLT_CD")
    public String code;

    @JsonProperty("PKLT_NM")
    private String name;

    @JsonProperty("ADDR")
    private String address;

    @JsonProperty("PRK_STTS_YN")
    private String canPark;

    @JsonProperty("TPKCT")
    private Double totalCapacity;

    @JsonProperty("NOW_PRK_VHCL_CNT")
    private Double nowParkedVehicle;

    @JsonProperty("PAY_YN")
    private String isPay;

    @JsonProperty("BSC_PRK_CRG")
    private Double basicParkCharge;

    @JsonProperty("SHRN_PKLT_YN")
    private String isShared;
}
