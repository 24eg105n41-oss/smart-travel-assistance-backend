package com.smarttravel.repository;

import com.smarttravel.entity.TravelPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelPlaceRepository extends JpaRepository<TravelPlace, Long> {
    List<TravelPlace> findByNameContainingIgnoreCase(String name);
    List<TravelPlace> findByCategoryIgnoreCase(String category);
}
