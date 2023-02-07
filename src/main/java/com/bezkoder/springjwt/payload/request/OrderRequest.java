package com.bezkoder.springjwt.payload.request;

import com.bezkoder.springjwt.models.OrderItem;
import com.bezkoder.springjwt.models.Review;
import com.bezkoder.springjwt.models.Shipping;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequest {
    private String username;
    private Integer itemsPrice;
    private Integer taxPrice;
    private Integer shippingPrice;
    private Integer totalPrice;
    private Boolean isPaid;
    private String paidAt;
    private Boolean isDelivered;
    private String deliveredAt;
    private String createdAt;
    private List<OrderItemRequest> orderItems;
    private ShippingRequest shipping;
}

