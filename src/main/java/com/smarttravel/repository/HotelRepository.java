package com.smarttravel.repository;

import com.smarttravel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByNameContainingIgnoreCase(String name);
    List<Hotel> findByPriceLessThanEqual(Double price);
    List<Hotel> findByRatingGreaterThanEqual(Double rating);
}
