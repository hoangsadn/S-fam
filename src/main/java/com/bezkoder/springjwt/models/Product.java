package com.bezkoder.springjwt.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String image;
    private String brand;
    private String price;
    private String category;
    private Integer countInStock;
    private String description;
    private Integer rating;
    private Integer numReviews;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "product")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Review> reviews  = new ArrayList<>();
//
//    @OneToOne(cascade = CascadeType.ALL,mappedBy = "product")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonIgnore
//    private OrderItem orderItem;
}
