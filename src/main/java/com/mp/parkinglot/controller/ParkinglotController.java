package com.mp.parkinglot.controller;

import com.mp.parkinglot.dto.ParkinglotRequest;
import com.mp.parkinglot.dto.ParkinglotResponse;
import com.mp.parkinglot.service.ParkinglotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parkinglot")
@RequiredArgsConstructor
public class ParkinglotController {
    private final ParkinglotService parkinglotService;

    @PostMapping("")
    public List<ParkinglotResponse> parkinglot(@RequestBody ParkinglotRequest parkinglots) {
        return parkinglotService.nearByParkinglots(parkinglots.getParkingLot());
    }

    @GetMapping("")
    public List<ParkinglotResponse> parkinglots(@RequestParam String district) {
        return parkinglotService.regionParkinglots(district);
    }

    @PostMapping("/empty")
    public List<ParkinglotResponse> emptyParkinglot(@RequestBody ParkinglotRequest parkinglots) {
        return parkinglotService.emptyParkinglots(parkinglots.getParkingLot());
    }

    @PostMapping("/free")
    public List<ParkinglotResponse> freeParkinglot(@RequestBody ParkinglotRequest parkinglots) {
        return parkinglotService.freeParkinglots(parkinglots.getParkingLot());
    }
}
