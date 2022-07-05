package com.micropos.delivery.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.micropos.delivery.dto.CartDto;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * OrderDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-07-05T19:57:54.465401800+08:00[Asia/Shanghai]")
public class OrderDto   {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("cart")
  private CartDto cart;

  @JsonProperty("total")
  private BigDecimal total;

  public OrderDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", required = false)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrderDto cart(CartDto cart) {
    this.cart = cart;
    return this;
  }

  /**
   * Get cart
   * @return cart
  */
  @NotNull @Valid 
  @Schema(name = "cart", required = true)
  public CartDto getCart() {
    return cart;
  }

  public void setCart(CartDto cart) {
    this.cart = cart;
  }

  public OrderDto total(BigDecimal total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
  */
  @NotNull @Valid 
  @Schema(name = "total", required = true)
  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderDto order = (OrderDto) o;
    return Objects.equals(this.id, order.id) &&
        Objects.equals(this.cart, order.cart) &&
        Objects.equals(this.total, order.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cart, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cart: ").append(toIndentedString(cart)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

