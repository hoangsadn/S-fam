package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer qty;
    private String image;
    private String price;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product ;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Order order ;

}
