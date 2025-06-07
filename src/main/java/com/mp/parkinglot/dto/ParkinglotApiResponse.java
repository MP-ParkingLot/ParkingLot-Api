package com.mp.parkinglot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class ParkinglotApiResponse {
    public String PKLT_CD;
    private String PKLT_NM;
    private String ADDR;
    private Long TPKCT;
    private String PRK_STTS_YN;
    private Long NOW_PRK_VHCL_CNT;
    private String PAY_YN;
    private String BSC_PRK_CRG;
    private String SHRN_PKLT_YN;
}
