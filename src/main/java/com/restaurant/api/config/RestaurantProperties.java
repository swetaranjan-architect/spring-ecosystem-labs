package com.restaurant.api.config;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "restaurant")
public record RestaurantProperties (
    @NotBlank String name,
    @NotBlank String version,
    @NotBlank String motto,
    @Min(1) @Max(50) int maxTablesPerOrder,
    @NotNull @Valid Security security
)
{
    public record Security(
            @NotBlank @Size(min=32) String jwtSecret,
            @Min(1) long jwtExpirationMinutes
    ){}
}
