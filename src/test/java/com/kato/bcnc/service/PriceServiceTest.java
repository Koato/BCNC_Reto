package com.kato.bcnc.service;

import com.kato.bcnc.domain.Price;
import com.kato.bcnc.dto.PriceRequest;
import com.kato.bcnc.dto.PriceResponse;
import com.kato.bcnc.exception.PriceNotFoundException;
import com.kato.bcnc.mapper.PriceMapper;
import com.kato.bcnc.repository.PriceRepository;
import com.kato.bcnc.service.impl.PriceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PriceServiceTest {

    @Mock
    PriceRepository repo;

    @Mock
    PriceMapper mapper;

    @InjectMocks
    PriceServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenPriceExists_thenReturnDTO() {
        var req = PriceRequest.builder()
                .brandId(1).productId(35455)
                .applicationDate(LocalDateTime.of(2020,6,14,10,0))
                .build();

        var entity = Price.builder().price(35.5).build();
        var dto    = PriceResponse.builder().price(35.5).build();

        when(repo.findApplicablePrice(1,35455,req.getApplicationDate()))
                .thenReturn(List.of(entity));
        when(mapper.toResponseDTO(entity)).thenReturn(dto);

        var res = service.getApplicablePrice(req);
        assertThat(res.getPrice()).isEqualTo(35.5);
    }

    @Test void whenNotFound_thenException() {
        var req = new PriceRequest(1,35455, LocalDateTime.now());
        when(repo.findApplicablePrice(any(),any(),any())).thenReturn(List.of());
        assertThatThrownBy(() -> service.getApplicablePrice(req))
                .isInstanceOf(PriceNotFoundException.class);
    }
}
