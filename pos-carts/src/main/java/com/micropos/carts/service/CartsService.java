package com.micropos.carts.service;

import com.micropos.carts.model.Cart;
import reactor.core.publisher.Mono;

public interface CartsService {

    Mono<Cart> add(Cart cart, String productId, int amount);

    Mono<Long> checkout(Cart cart);
}
