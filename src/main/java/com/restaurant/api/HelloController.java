package com.restaurant.api;


import com.restaurant.api.config.RestaurantProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final RestaurantProperties props;

    @GetMapping("/hello")
    public String hello(){
        return "Welcome to the %s %s %s".formatted(props.name(), props.version(), props.motto());
    }
}
