package com.micropos.carts.mapper;

import com.micropos.carts.dto.CartDto;
import com.micropos.carts.dto.ItemDto;
import com.micropos.carts.model.*;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface CartsMapper {
    Cart toCart(CartDto cartDto);
    CartDto toCartDto(Cart cart);
    Item map(ItemDto value);
}
