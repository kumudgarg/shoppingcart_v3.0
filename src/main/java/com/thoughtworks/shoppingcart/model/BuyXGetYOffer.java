package com.thoughtworks.shoppingcart.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@Data
@NoArgsConstructor
public class BuyXGetYOffer {

    @JsonIgnore
    private UUID id;

    @NotNull
    @NotBlank
    private int buyQuantity;

    @NotNull
    @NotBlank
    private int freeQuantity;

    public double getDiscount(Product product, int quantity) {
        if (freeQuantity != 0 && buyQuantity != 0) {
            int freeItems = (quantity / (freeQuantity + buyQuantity)) * freeQuantity;
            return freeItems * product.getPrice();
        }
        return 0.0;
    }

    public UUID getId() {
        id = UUID.randomUUID();
        return id;
    }

}
