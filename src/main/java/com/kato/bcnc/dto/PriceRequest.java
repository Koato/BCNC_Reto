package com.kato.bcnc.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PriceRequest {

    @NotNull(message = "brandId es obligatorio")
    private Integer brandId;

    @NotNull(message = "productId es obligatorio")
    private Integer productId;

    @NotNull(message = "applicationDate es obligatorio")
    private LocalDateTime applicationDate;
}
