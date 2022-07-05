package com.micropos.carts.service;

import com.micropos.carts.dto.CartDto;
import com.micropos.carts.dto.OrderDto;
import com.micropos.carts.mapper.CartsMapper;
import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Item;
import com.micropos.carts.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CartsServiceImpl implements CartsService, Serializable {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerFactory factory;

    @Resource
    private CartsMapper cartsMapper;

    @Override
    public Mono<Cart> add(Cart cart, String productId, int amount) {
        CircuitBreaker cb = factory.create("circuitbreaker");
        Cart cart_ = cb.run(() -> {
            ResponseEntity<Product> productResponseEntity = restTemplate.
                    getForEntity("http://pos-products/api/products/" + productId, Product.class);
            Product product = productResponseEntity.getBody();
            if (product == null) return cart;
            cart.addItem(new Item(product, amount));
            return cart;
        }, ex -> {
            cart.addItem(new Item(new Product(productId, "故障", 0, ""), amount));
            return cart;
        });
        return Mono.just(cart_);
    }

    @Override
    public Mono<Long> checkout(Cart cart) {
        CircuitBreaker circuitBreaker = factory.create("circuitbreaker");
        Long value = circuitBreaker.run(() -> {
            CartDto cartDto = cartsMapper.toCartDto(cart);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<CartDto> httpEntity1 = new HttpEntity<>(cartDto, headers);
            ResponseEntity<BigDecimal> bigDecimalResponseEntity = restTemplate
                    .postForEntity("http://pos-counter/api/counter/checkout", httpEntity1, BigDecimal.class);
            BigDecimal total = bigDecimalResponseEntity.getBody();

            OrderDto orderDto = new OrderDto();
            orderDto.setTotal(total);
            orderDto.setCart(cartDto);
            HttpEntity<OrderDto> httpEntity2 = new HttpEntity<>(orderDto, headers);
            ResponseEntity<Long> orderResponseEntity = restTemplate
                    .postForEntity("http://pos-order/api/order", httpEntity2, Long.class);

            cart.getItems().clear();
            return orderResponseEntity.getBody();
        }, ex -> {
            return Long.valueOf(-1);
        });
        return Mono.just(value);
    }
}
