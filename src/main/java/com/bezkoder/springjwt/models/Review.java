package com.bezkoder.springjwt.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "reviews")

public class Review {
    private String name;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt = LocalDateTime.now();

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumn(
            name = "product_id"
            //referencedColumnName = "id"
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Product product;
}
