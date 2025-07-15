package com.kato.bcnc.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "PRICES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Price implements Serializable {

    @Valid
    @EmbeddedId
    private PriceId priceId;

    @Column(name="PRICE_LIST")
    private Integer priceList;

    @Column(name="PRIORITY")
    private Integer priority;

    @Column(name="PRICE")
    private Double price;

    @Column(name="CURR")
    private String curr;
}