package com.micropos.carts.mapper;

import com.micropos.carts.dto.CartDto;
import com.micropos.carts.dto.ItemDto;
import com.micropos.carts.dto.ProductDto;
import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Item;
import com.micropos.carts.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class CartsMapperImpl implements CartsMapper {

    @Override
    public Cart toCart(CartDto cartDto) {
        if ( cartDto == null ) {
            return null;
        }

        Cart cart = new Cart();

        cart.setItems( itemDtoListToItemList( cartDto.getItems() ) );

        return cart;
    }

    @Override
    public CartDto toCartDto(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartDto cartDto = new CartDto();

        cartDto.setItems( itemListToItemDtoList( cart.getItems() ) );

        return cartDto;
    }

    @Override
    public Item map(ItemDto value) {
        if ( value == null ) {
            return null;
        }

        Item item = new Item();

        return item;
    }

    protected List<Item> itemDtoListToItemList(List<ItemDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Item> list1 = new ArrayList<Item>( list.size() );
        for ( ItemDto itemDto : list ) {
            list1.add( map( itemDto ) );
        }

        return list1;
    }

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setPrice( product.getPrice() );
        productDto.setImage( product.getImage() );

        return productDto;
    }

    protected ItemDto itemToItemDto(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemDto itemDto = new ItemDto();

        itemDto.setProduct( productToProductDto( item.getProduct() ) );
        itemDto.setQuantity( item.getQuantity() );

        return itemDto;
    }

    protected List<ItemDto> itemListToItemDtoList(List<Item> list) {
        if ( list == null ) {
            return null;
        }

        List<ItemDto> list1 = new ArrayList<ItemDto>( list.size() );
        for ( Item item : list ) {
            list1.add( itemToItemDto( item ) );
        }

        return list1;
    }
}
