package com.micropos.counter.rest;


import com.micropos.counter.api.CounterApi;
import com.micropos.counter.dto.CartDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class CounterController implements CounterApi {

    @Override
    public ResponseEntity<BigDecimal> checkout(CartDto cart) {
        return new ResponseEntity<>(cart.getItems()
                .stream()
                .map(item -> item.getQuantity() * item.getProduct().getPrice())
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add),
                HttpStatus.OK
        );
    }

}

