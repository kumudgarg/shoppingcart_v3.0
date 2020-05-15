package com.thoughtworks.shoppingcart.repository;

import com.thoughtworks.shoppingcart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

}
