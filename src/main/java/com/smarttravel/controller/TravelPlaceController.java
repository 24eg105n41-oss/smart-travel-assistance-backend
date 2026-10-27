package com.smarttravel.controller;

import com.smarttravel.dto.TravelPlaceDTO;
import com.smarttravel.service.TravelPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/travelplaces", "/travelplaces"})
public class TravelPlaceController {

    @Autowired
    private TravelPlaceService travelPlaceService;

    @PostMapping
    public ResponseEntity<TravelPlaceDTO> addPlace(@RequestBody TravelPlaceDTO dto) {
        TravelPlaceDTO saved = travelPlaceService.addPlace(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<TravelPlaceDTO>> getAllPlaces() {
        return ResponseEntity.ok(travelPlaceService.getAllPlaces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelPlaceDTO> getPlaceById(@PathVariable Long id) {
        TravelPlaceDTO dto = travelPlaceService.getPlaceById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<TravelPlaceDTO>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(travelPlaceService.searchByName(name));
    }

    @GetMapping("/search/category")
    public ResponseEntity<List<TravelPlaceDTO>> searchByCategory(@RequestParam String category) {
        return ResponseEntity.ok(travelPlaceService.searchByCategory(category));
    }
}
