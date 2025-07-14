package com.kato.bcnc.service;

import com.kato.bcnc.dto.PriceRequest;
import com.kato.bcnc.dto.PriceResponse;

public interface PriceService {

    PriceResponse getApplicablePrice(PriceRequest request);
}
