package com.thoughtworks.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    private double price;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<BuyXGetYOffer> offer;

    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.offer = null;
    }
}
