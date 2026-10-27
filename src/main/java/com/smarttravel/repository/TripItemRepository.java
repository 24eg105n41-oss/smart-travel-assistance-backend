package com.smarttravel.repository;

import com.smarttravel.entity.TripItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TripItemRepository extends JpaRepository<TripItem, Long> {
    List<TripItem> findByTripIdOrderByDayNumberAscOrderIndexAsc(Long tripId);
}
