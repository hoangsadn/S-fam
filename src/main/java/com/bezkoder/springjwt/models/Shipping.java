package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "shipping")
public class Shipping {

    @Id
    @GeneratedValue
    private Long id;

    private String address;
    private String city;
    private String postalCode;
    private String country;



    @OneToOne(cascade = CascadeType.ALL,mappedBy = "shipping")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    private Order order;

}
