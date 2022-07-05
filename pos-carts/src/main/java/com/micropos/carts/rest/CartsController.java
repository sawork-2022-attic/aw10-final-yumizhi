package com.micropos.carts.rest;

import com.micropos.carts.api.CartApi;
import com.micropos.carts.dto.CartDto;
import com.micropos.carts.mapper.CartsMapper;
import com.micropos.carts.model.Cart;
import com.micropos.carts.service.CartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("api")
public class CartsController implements CartApi {

    private final CartsMapper cartsMapper;

    private final CartsService cartsService;

    Cart cart;

    @Autowired
    public CartsController(CartsService cartService, Cart cart, CartsMapper cartsMapper) {
        this.cartsService = cartService;
        this.cart = cart;
        this.cartsMapper = cartsMapper;
    }

    @Override
    public Mono<ResponseEntity<CartDto>> addToCart(String productId, ServerWebExchange exchange) {
        return cartsService.add(cart, productId, 1)
                .map(cartsMapper::toCartDto)
                .map(cartDto -> new ResponseEntity<>(cartDto, HttpStatus.OK));
    }

    @Override
    public Mono<ResponseEntity<CartDto>> listCart(ServerWebExchange exchange) {
        return Mono.just(new ResponseEntity<>(cartsMapper.toCartDto(cart), HttpStatus.OK));
    }

    @Override
    public Mono<ResponseEntity<Long>> checkoutCart(ServerWebExchange exchange) {
        return cartsService.checkout(cart)
                .map(cartId -> new ResponseEntity<>(cartId, HttpStatus.OK));
    }
}
