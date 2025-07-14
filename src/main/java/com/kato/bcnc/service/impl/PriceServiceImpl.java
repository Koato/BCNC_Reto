package com.kato.bcnc.service.impl;

import com.kato.bcnc.dto.PriceRequest;
import com.kato.bcnc.dto.PriceResponse;
import com.kato.bcnc.mapper.PriceMapper;
import com.kato.bcnc.repository.PriceRepository;
import com.kato.bcnc.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository repository;
    private final PriceMapper mapper;

    @Override
    public PriceResponse getApplicablePrice(PriceRequest request) {
        return repository.findApplicablePrice(request.getBrandId(), request.getProductId(), request.getApplicationDate())
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new NoSuchElementException("No price found"));
    }
}
