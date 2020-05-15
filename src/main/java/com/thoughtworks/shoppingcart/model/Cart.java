package com.thoughtworks.shoppingcart.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.shoppingcart.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "cart")
public class Cart {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<CartOffer> cartOffer;



    private double salesTax;


    private double discount;


    private double grandTotal;

    public Cart() {
    }

    public Cart(Long id, double salesTax, double discount, double grandTotal) {
        this.id = id;
        this.salesTax = salesTax;
        this.discount = discount;
        this.grandTotal = grandTotal;
    }
}
