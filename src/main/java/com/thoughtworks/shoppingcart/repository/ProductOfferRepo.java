package com.thoughtworks.shoppingcart.repository;

import com.thoughtworks.shoppingcart.model.BuyXGetYOffer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ProductOfferRepo {

    public static Map<UUID, BuyXGetYOffer> buyXGetYOfferMap = new HashMap<>();

    public BuyXGetYOffer findByProductOfferId(UUID id) {
        BuyXGetYOffer buyXGetYOffer = buyXGetYOfferMap.get(id);
        return buyXGetYOffer;
    }


}
