package com.thoughtworks.shoppingcart.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@Data
@NoArgsConstructor
@Entity
public class BuyXGetYOffer {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private int buyQuantity;

    private int freeQuantity;

    public BuyXGetYOffer(Long id, int buyQuantity, int freeQuantity) {
        this.id = id;
        this.buyQuantity = buyQuantity;
        this.freeQuantity = freeQuantity;
    }

    public double getDiscount(Product product, int quantity) {
        if (freeQuantity != 0 && buyQuantity != 0) {
            int freeItems = (quantity / (freeQuantity + buyQuantity)) * freeQuantity;
            return freeItems * product.getPrice();
        }
        return 0.0;
    }


}
