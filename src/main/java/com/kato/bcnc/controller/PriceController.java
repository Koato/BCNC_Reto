package com.kato.bcnc.controller;

import com.kato.bcnc.dto.PriceRequest;
import com.kato.bcnc.dto.PriceResponse;
import com.kato.bcnc.exception.PriceNotFoundException;
import com.kato.bcnc.service.PriceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
@Validated
public class PriceController {

    private final PriceService service;

    /**
     * API FIRST: definimos contrato aquí
     */
    @PostMapping("/search")
    public ResponseEntity<PriceResponse> findPrice(@Valid @RequestBody PriceRequest request) {
        try {
            return ResponseEntity.ok(service.getApplicablePrice(request));
        } catch (PriceNotFoundException e) {
            System.err.println("PriceNotFoundException: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
