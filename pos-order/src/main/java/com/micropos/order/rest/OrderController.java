package com.micropos.order.rest;


import com.micropos.order.api.OrderApi;
import com.micropos.order.dto.OrderDto;
import com.micropos.order.service.OrderService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("api")
public class OrderController implements OrderApi {

    @Resource
    private OrderService orderService;

    @Override
    public Mono<ResponseEntity<Long>> createOrder(Mono<OrderDto> orderDto, ServerWebExchange exchange) {
        return orderDto.flatMap(order -> {
            return orderService.createOrder(order).map(id -> {
                return ResponseEntity.status(HttpStatus.CREATED).body(id);
            });
        });
    }

    @Override
    public Mono<ResponseEntity<OrderDto>> getOrder(Long id, ServerWebExchange exchange) {
        return orderService.getOrder(id).map(order -> {
            return ResponseEntity.ok(order);
        });
    }
}
