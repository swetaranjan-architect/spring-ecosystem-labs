package com.restaurant.api.domain.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public interface DishRepository extends JpaRepository<Dish,Long> {

    Optional<Dish> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);

    List<Dish> findByPriceCentsLessThanOrderByPriceCentsAsc(Integer maxCents);

    @Query("Select d from Dish d where d.priceCents between :min and :max")
    List<Dish> findInPriceRanges(Integer min, Integer max);


    @Query(value = "select * from dish where price_cents < ?1",nativeQuery = true)
    List<Dish> cheaperThanNative(int max);

    @Modifying
    @Transactional
    @Query("update Dish d set d.priceCents = d.priceCents + :bump where d.id = :id")
    int raisePrice(@Param("id") Long id, @Param("bump" ) int bump);

    interface DishView {
        String getName();
        Integer getPriceCents();
    }
    List<DishView> findBy();

//    record DishDto(Long id,String name, Integer priceCents){
//        @Query("Select new com.restaurant.api.domain.dish.DishRepository$DishDto(d.id,d.name,d.priceCents) from Dish d")
//        List<DishDto> listAsDto();
}
