package com.kato.bcnc.dto;

import lombok.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PriceRequest {

    private Integer brandId;
    private Integer productId;
    private LocalDateTime applicationDate;
}
