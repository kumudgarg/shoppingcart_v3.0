package com.thoughtworks.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cartOffer")
public class CartOffer {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private double discountRate;


    private double leastBuyPrice;

    @JsonIgnore
    private double cartDiscount = 0.0;

    public CartOffer(Long id, double discountRate, double leastBuyPrice) {
        this.id = id;
        this.discountRate = discountRate;
        this.leastBuyPrice = leastBuyPrice;
    }

    public double getDiscountByCartOffer(double totalPrice, double discount) {
        if (totalPrice > leastBuyPrice) {
            cartDiscount = ((totalPrice - discount) * discountRate) / 100;
        }
        return cartDiscount;
    }

}
