package com.bezkoder.springjwt.payload.request;

import com.bezkoder.springjwt.models.OrderItem;
import com.bezkoder.springjwt.models.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequest {

    private String name;
    private String image;
    private String brand;
    private String price;
    private String category;
    private Integer countInStock;
    private String description;
    private Integer rating;
    private Integer numReviews;

}
