package com.thoughtworks.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartOffer {

    @JsonIgnore
    private UUID id;

    @NotNull
    @NotBlank
    private double discountRate;

    @NotNull
    @NotBlank
    private int leastBuyPrice;

    @JsonIgnore
    private double cartDiscount = 0.0;

    public double getDiscountByCartOffer(double totalPrice, double discount) {
        if (totalPrice > leastBuyPrice) {
            cartDiscount = ((totalPrice - discount) * discountRate) / 100;
        }
        return cartDiscount;
    }

    public UUID getId() {
        id = UUID.randomUUID();
        return id;
    }

}
