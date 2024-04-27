package com.prasanna.Food.request;

import com.prasanna.Food.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;
    private Address deliveryaddress;

}
