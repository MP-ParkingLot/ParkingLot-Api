package com.mp.parkinglot.service;

import com.mp.parkinglot.dto.ParkinglotApiResponse;
import com.mp.parkinglot.dto.ParkinglotResponse;
import com.mp.parkinglot.strings.Ratio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkinglotService {
    private final ParkinglotRequestService parkinglotRequestService;

    public List<ParkinglotResponse> nearByParkinglots(List<String> parkinglots) {
        List<ParkinglotApiResponse> parkinglotApiResponses = parkinglotRequestService.parkinglotRequest();

        List<ParkinglotApiResponse> result = parkinglotApiResponses.stream()
                .filter( loc -> parkinglots.stream().anyMatch( p -> p.equals(loc.getCode()) ))
                .collect(Collectors.toList());

        List<ParkinglotResponse> response = new ArrayList<>();
        result.forEach(pl ->
                response.add(new ParkinglotResponse(
                        pl.getCode(),
                        (pl.getTotalCapacity() - pl.getNowParkedVehicle()),
                        pl.getTotalCapacity(),
                        Ratio.getRatio(pl.getNowParkedVehicle(), pl.getTotalCapacity()).toString(),
                        pl.getBasicParkCharge()
                ))
        );

        return response;
    }
    public List<ParkinglotResponse> regionParkinglots(String region) {
        List<ParkinglotApiResponse> result = parkinglotRequestService.parkinglotRequest(region);

        List<ParkinglotResponse> response = new ArrayList<>();
        result.forEach(pl ->
                response.add(new ParkinglotResponse(
                        pl.getCode(),
                        (pl.getTotalCapacity() - pl.getNowParkedVehicle()),
                        pl.getTotalCapacity(),
                        Ratio.getRatio(pl.getNowParkedVehicle(), pl.getTotalCapacity()).toString(),
                        pl.getBasicParkCharge()
                ))
        );

        return response;
    }
    public List<ParkinglotResponse> emptyParkinglots(List<String> parkinglots) {
        List<ParkinglotApiResponse> parkinglotApiResponses = parkinglotRequestService.parkinglotRequest();

        List<ParkinglotApiResponse> result = parkinglotApiResponses.stream()
                .filter( loc -> parkinglots.stream().anyMatch( p -> p.equals(loc.getCode()) ))
                .collect(Collectors.toList());

        List<ParkinglotResponse> temp = new ArrayList<>();
        result.forEach(pl ->
                temp.add(new ParkinglotResponse(
                        pl.getCode(),
                        (pl.getTotalCapacity() - pl.getNowParkedVehicle()),
                        pl.getTotalCapacity(),
                        Ratio.getRatio(pl.getNowParkedVehicle(), pl.getTotalCapacity()).toString(),
                        pl.getBasicParkCharge()
                ))
        );

        List<ParkinglotResponse> response = temp
                .stream()
                .filter( pl -> !pl.getRatio().equals(Ratio.FULL.toString()) )
                .collect(Collectors.toList());

        return response;
    }
    public List<ParkinglotResponse> freeParkinglots(List<String> parkinglots) {
        List<ParkinglotApiResponse> parkinglotApiResponses = parkinglotRequestService.parkinglotRequest();

        List<ParkinglotApiResponse> result = parkinglotApiResponses.stream()
                .filter( loc -> parkinglots.stream().anyMatch( p -> p.equals(loc.getCode()) ))
                .collect(Collectors.toList());

        List<ParkinglotApiResponse> temp = new ArrayList<>();

        temp = result
                .stream()
                .filter( pl -> pl.getIsPay().equals("Y") )
                .collect(Collectors.toList());

        List<ParkinglotResponse> response = new ArrayList<>();

        temp.forEach(pl ->
                response.add(new ParkinglotResponse(
                        pl.getCode(),
                        (pl.getTotalCapacity() - pl.getNowParkedVehicle()),
                        pl.getTotalCapacity(),
                        Ratio.getRatio(pl.getNowParkedVehicle(), pl.getTotalCapacity()).toString(),
                        pl.getBasicParkCharge()
                ))
        );

        return response;
    }
}
