package com.smarttravel.controller;

import com.smarttravel.dto.RestaurantDTO;
import com.smarttravel.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO dto) {
        RestaurantDTO saved = restaurantService.addRestaurant(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long id) {
        RestaurantDTO dto = restaurantService.getRestaurantById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<RestaurantDTO>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(restaurantService.searchByName(name));
    }

    @GetMapping("/filter/cuisine")
    public ResponseEntity<List<RestaurantDTO>> filterByCuisine(@RequestParam String cuisine) {
        return ResponseEntity.ok(restaurantService.filterByCuisine(cuisine));
    }
}
