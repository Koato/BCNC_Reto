package com.kato.bcnc.mapper;

import com.kato.bcnc.domain.Price;
import com.kato.bcnc.dto.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(source="priceId.brandId",   target="brandId")
    @Mapping(source="priceId.productId", target="productId")
    @Mapping(source="priceList",         target="priceList")
    @Mapping(source="priceId.startDate", target="startDate")
    @Mapping(source="priceId.endDate",   target="endDate")
    PriceResponse toResponseDTO(Price price);
}
