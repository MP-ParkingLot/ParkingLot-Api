package com.mp.parkinglot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mp.parkinglot.dto.ParkinglotApiResponse;
import com.mp.parkinglot.dto.ParkinglotResult;
import com.mp.parkinglot.dto.ParkinglotResultWrapper;
import com.mp.parkinglot.exception.CustomException;
import com.mp.parkinglot.exception.ErrorCode;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.util.List;

@Service
public class ParkinglotRequestService {
    private final String API_URL = System.getenv("API_URL");
    private final String KEY = System.getenv("API_ID");     // 인증키
    private final String TYPE = "json";            // 요청파일타입 - xml, xmlf, xls, json
    private final String SERVICE = "GetParkingInfo";         // 서비스명 - GetParkingInfo
    private final String START_INDEX = "1";    // 데이터 행 시작번호
    private final String END_INDEX = "5";      // 데이터 행 끝번호

    public List<ParkinglotApiResponse> parkinglotRequest() {
        try {
            StringBuilder urlBuilder = new StringBuilder(API_URL);
            urlBuilder.append("/" +  URLEncoder.encode(KEY,"UTF-8") );
            urlBuilder.append("/" +  URLEncoder.encode(TYPE,"UTF-8") );
            urlBuilder.append("/" + URLEncoder.encode(SERVICE,"UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(START_INDEX,"UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(END_INDEX,"UTF-8"));

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
            BufferedReader rd;

            // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                throw new CustomException(ErrorCode.FAILED_API_REQUEST);
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();

            ObjectMapper objectMapper = new ObjectMapper();
            ParkinglotResultWrapper wrapper = objectMapper.readValue(sb.toString(), ParkinglotResultWrapper.class);
            List<ParkinglotApiResponse> resultList = wrapper.GetParkingInfo.row;

            conn.disconnect();
            return resultList;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public List<ParkinglotApiResponse> parkinglotRequest(String ADDR) {
        try {
            StringBuilder urlBuilder = new StringBuilder(API_URL);
            urlBuilder.append("/" +  URLEncoder.encode(KEY,"UTF-8") );
            urlBuilder.append("/" +  URLEncoder.encode(TYPE,"UTF-8") );
            urlBuilder.append("/" + URLEncoder.encode(SERVICE,"UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(START_INDEX,"UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(END_INDEX,"UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(ADDR,"UTF-8"));       // 선택: 자치구명

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
            BufferedReader rd;

            // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();

            ObjectMapper objectMapper = new ObjectMapper();
            ParkinglotResultWrapper wrapper = objectMapper.readValue(sb.toString(), ParkinglotResultWrapper.class);
            List<ParkinglotApiResponse> resultList = wrapper.GetParkingInfo.row;

            conn.disconnect();

            return resultList;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
