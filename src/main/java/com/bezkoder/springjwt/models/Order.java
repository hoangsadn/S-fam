package com.bezkoder.springjwt.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product_order")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private Integer itemsPrice;
    private Integer taxPrice;
    private Integer shippingPrice;
    private Integer totalPrice;
    private Boolean isPaid;
    private String paidAt;
    private Boolean isDelivered;
    private String deliveredAt;


    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "order")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<OrderItem> orderItems  = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Shipping shipping;
}
