package com.bezkoder.springjwt.payload.request;

import com.bezkoder.springjwt.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemRequest {

    private String name;
    private Integer qty;
    private String image;
    private String price;
    private Integer product ;

}
