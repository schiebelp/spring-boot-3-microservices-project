package com.microservices.orderservice.mapper;

import com.microservices.orderservice.dto.OrderLineItemDTO;
import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.dto.OrderResponse;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.model.OrderLineItems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", //managed by Spring as a singleton :]
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderNumber", source = "uuid")
    @Mapping(target = "orderLineItems", source = "request.items")
    Order toModel(OrderRequest request, String uuid);

    @Mapping(target = "id", ignore = true)
    OrderLineItems toModel(OrderLineItemDTO dto);

    @Mapping(target = "orderLineItemsDTO", source = "orderLineItems")
    OrderResponse toDto(Order order);


}
