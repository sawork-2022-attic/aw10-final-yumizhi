package com.micropos.delivery.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Order {

    private Long id;

    private byte[] cart;

    private BigDecimal total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public byte[] getCart() {
        return cart;
    }

    public void setCart(byte[] cart) {
        this.cart = cart;
    }
}
