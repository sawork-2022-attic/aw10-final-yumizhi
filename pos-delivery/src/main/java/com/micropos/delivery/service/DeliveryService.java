package com.micropos.delivery.service;

import com.micropos.delivery.dto.DeliveryDto;
import reactor.core.publisher.Mono;

public interface DeliveryService {

    Mono<DeliveryDto> findDeliveryByOrderId(Long orderId);
    Mono<DeliveryDto> getDeliveryById(Long deliveryId);
    Mono<Long> createDelivery(DeliveryDto delivery);
}
