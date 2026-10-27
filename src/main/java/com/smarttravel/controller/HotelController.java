package com.smarttravel.controller;

import com.smarttravel.dto.HotelDTO;
import com.smarttravel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/hotels", "/hotels"})
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDTO> addHotel(@RequestBody HotelDTO dto) {
        HotelDTO saved = hotelService.addHotel(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long id) {
        HotelDTO dto = hotelService.getHotelById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<HotelDTO>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(hotelService.searchByName(name));
    }

    @GetMapping("/filter/price")
    public ResponseEntity<List<HotelDTO>> filterByPrice(@RequestParam Double maxPrice) {
        return ResponseEntity.ok(hotelService.filterByPrice(maxPrice));
    }

    @GetMapping("/filter/rating")
    public ResponseEntity<List<HotelDTO>> filterByRating(@RequestParam Double minRating) {
        return ResponseEntity.ok(hotelService.filterByRating(minRating));
    }
}
