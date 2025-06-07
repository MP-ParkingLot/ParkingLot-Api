package com.mp.parkinglot.controller;

import com.mp.parkinglot.dto.ParkinglotRequest;
import com.mp.parkinglot.dto.ParkinglotResponse;
import com.mp.parkinglot.service.ParkinglotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parkinglot")
@RequiredArgsConstructor
@Slf4j
public class ParkinglotController {
    private final ParkinglotService parkinglotService;

    @PostMapping("")
    public List<ParkinglotResponse> parkinglots(@RequestBody ParkinglotRequest parkinglots) {
        log.info("parkinglots: {}", parkinglots);
        return parkinglotService.nearByParkinglots(parkinglots.getParkingLot());
    }

    @GetMapping("")
    public List<ParkinglotResponse> regionParkinglots(@RequestParam String district) {
        log.info("region parkinglots: {}", district);
        return parkinglotService.regionParkinglots(district);
    }

    @PostMapping("/empty")
    public List<ParkinglotResponse> emptyParkinglots(@RequestBody ParkinglotRequest parkinglots) {
        log.info("empty parkinglots: {}", parkinglots);
        return parkinglotService.emptyParkinglots(parkinglots.getParkingLot());
    }

    @PostMapping("/free")
    public List<ParkinglotResponse> freeParkinglots(@RequestBody ParkinglotRequest parkinglots) {
        log.info("free parkinglots: {}", parkinglots);
        return parkinglotService.freeParkinglots(parkinglots.getParkingLot());
    }
}
