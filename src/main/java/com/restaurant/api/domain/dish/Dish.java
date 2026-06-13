package com.restaurant.api.domain.dish;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name="dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Setter
    @Column(nullable = false,unique = true)
    private String name;

    @Setter
    @Column(name = "price_cents",nullable = false)
    private Integer priceCents;

    protected Dish(){}

    public Dish(String name,Integer priceCents){
        this.name = name;
        this.priceCents = priceCents;
    }

}
