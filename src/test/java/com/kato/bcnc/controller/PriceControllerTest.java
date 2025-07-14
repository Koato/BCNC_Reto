package com.kato.bcnc.controller;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kato.bcnc.dto.PriceRequest;
import com.kato.bcnc.dto.PriceResponse;
import com.kato.bcnc.service.PriceService;
import com.kato.bcnc.service.impl.PriceServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    private MockMvc mvc;
    private ObjectMapper mapper;

    @Mock
    private PriceServiceImpl service;

    @InjectMocks
    private PriceController controller;

    @BeforeEach
    void setup() {
        // Configuramos ObjectMapper con JavaTimeModule
        mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Le decimos a MockMvc que use ese mapper para JSON
        var converter = new MappingJackson2HttpMessageConverter(mapper);
        mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setMessageConverters(converter)
                .build();
    }

    @Test
    void testPostPriceWithBodyParams() throws Exception {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        PriceRequest req = new PriceRequest(1, 35455, applicationDate);
        PriceResponse res = PriceResponse.builder()
                .brandId(1)
                .productId(35455)
                .priceList(1)
                .startDate(applicationDate)
                .endDate(applicationDate.plusHours(2))
                .price(35.50)
                .build();

        // Stub flojo: cualquier PriceRequest
        doReturn(res)
                .when(service)
                .getApplicablePrice(any(PriceRequest.class));

        // Act & Assert
        mvc.perform(post("/api/v1/prices/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("35.5"));
    }
}
