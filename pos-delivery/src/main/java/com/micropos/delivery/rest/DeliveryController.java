package com.micropos.delivery.rest;

import com.micropos.delivery.api.DeliveryApi;
import com.micropos.delivery.dto.DeliveryDto;
import com.micropos.delivery.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@RequestMapping("api")
public class DeliveryController implements DeliveryApi {

    @Resource
    private DeliveryService deliveryService;

    @Override
    public Mono<ResponseEntity<DeliveryDto>> findDeliveryByOrderId(Long orderId, ServerWebExchange exchange) {
        return deliveryService.findDeliveryByOrderId(orderId).map(delivery -> {
            return ResponseEntity.ok(delivery);
        });
    }

    @Override
    public Mono<ResponseEntity<DeliveryDto>> getDeliveryById(Long id, ServerWebExchange exchange) {
        return deliveryService.getDeliveryById(id).map(delivery -> {
            return ResponseEntity.ok(delivery);
        });
    }
}
