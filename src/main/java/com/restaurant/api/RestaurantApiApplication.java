package com.restaurant.api;

import com.restaurant.api.config.RestaurantProperties;
import com.restaurant.api.domain.dish.Dish;
import com.restaurant.api.domain.dish.DishRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableConfigurationProperties(RestaurantProperties.class)
public class RestaurantApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantApiApplication.class, args);
    }


    @Bean
    CommandLineRunner seed(DishRepository dishRepository){
        return args -> {
            if(dishRepository.count() == 0){
                dishRepository.save(new Dish("Pizza",1200));
                dishRepository.save(new Dish("Tiramisu",800));
            }

            System.out.println("Dishes in pantry: " + dishRepository.count());
            dishRepository.findByNameIgnoreCase("pizza")
                    .ifPresent(d -> System.out.println("Found "+ d.getName()
                            + " @" + d.getPriceCents() + "c"));
        };
    }



}



