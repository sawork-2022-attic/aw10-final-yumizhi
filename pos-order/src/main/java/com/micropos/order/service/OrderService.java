package com.micropos.order.service;

import com.micropos.order.dto.OrderDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

public interface OrderService {

    public Mono<Long> createOrder(OrderDto orderDto);

    Mono<OrderDto> getOrder(Long id);
}
