package com.smarttravel.controller;

import com.smarttravel.dto.TripDTO;
import com.smarttravel.dto.TripItemDTO;
import com.smarttravel.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping
    public ResponseEntity<?> createTrip(@RequestBody TripDTO dto, @RequestParam Long userId) {
        try {
            TripDTO created = tripService.createTrip(dto, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<TripDTO>> getTrips(@RequestParam Long userId) {
        return ResponseEntity.ok(tripService.getTripsByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTripById(@PathVariable Long id, @RequestParam Long userId) {
        TripDTO trip = tripService.getTripById(id, userId);
        if (trip == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(trip);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long id, @RequestParam Long userId) {
        boolean deleted = tripService.deleteTrip(id, userId);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("message", "Trip deleted"));
    }

    @PostMapping("/{tripId}/items")
    public ResponseEntity<?> addItem(@PathVariable Long tripId, @RequestBody TripItemDTO dto, @RequestParam Long userId) {
        TripItemDTO item = tripService.addTripItem(tripId, dto, userId);
        if (item == null) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<?> removeItem(@PathVariable Long itemId, @RequestParam Long userId) {
        boolean deleted = tripService.removeTripItem(itemId, userId);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("message", "Item removed"));
    }
}
