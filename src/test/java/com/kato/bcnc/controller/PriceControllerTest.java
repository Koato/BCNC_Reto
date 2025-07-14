package com.kato.bcnc.controller;

import com.kato.bcnc.dto.PriceRequest;
import com.kato.bcnc.dto.PriceResponse;
import com.kato.bcnc.service.PriceService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    MockMvc mvc;

    @Mock
    PriceService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    void search_ok() throws Exception {
        var req = new PriceRequest(1,35455, LocalDateTime.of(2020,6,14,10,0));
        var res = PriceResponse.builder().price(35.5).build();
        when(service.getApplicablePrice(req)).thenReturn(res);

        mvc.perform(post("/api/v1/prices/search")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.5));
    }
}
