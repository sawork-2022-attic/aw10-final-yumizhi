package com.micropos.counter.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.micropos.counter.dto.ProductDto;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ItemDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-07-05T19:55:54.644725200+08:00[Asia/Shanghai]")
public class ItemDto   {

  @JsonProperty("product")
  private ProductDto product;

  @JsonProperty("quantity")
  private Integer quantity;

  public ItemDto product(ProductDto product) {
    this.product = product;
    return this;
  }

  /**
   * Get product
   * @return product
  */
  @NotNull @Valid 
  @Schema(name = "product", required = true)
  public ProductDto getProduct() {
    return product;
  }

  public void setProduct(ProductDto product) {
    this.product = product;
  }

  public ItemDto quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
  */
  @NotNull 
  @Schema(name = "quantity", required = true)
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemDto item = (ItemDto) o;
    return Objects.equals(this.product, item.product) &&
        Objects.equals(this.quantity, item.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, quantity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemDto {\n");
    sb.append("    product: ").append(toIndentedString(product)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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

