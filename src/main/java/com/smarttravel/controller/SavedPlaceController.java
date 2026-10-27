package com.smarttravel.controller;

import com.smarttravel.dto.SavedPlaceDTO;
import com.smarttravel.service.SavedPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/saved")
public class SavedPlaceController {

    @Autowired
    private SavedPlaceService savedPlaceService;

    @PostMapping
    public ResponseEntity<?> savePlace(@RequestBody SavedPlaceDTO dto, @RequestParam Long userId) {
        try {
            SavedPlaceDTO saved = savedPlaceService.savePlace(dto, userId);
            if (saved == null) return ResponseEntity.badRequest().body(Map.of("error", "Could not save place"));
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<SavedPlaceDTO>> getSaved(@RequestParam Long userId) {
        return ResponseEntity.ok(savedPlaceService.getSavedPlacesByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeSaved(@PathVariable Long id, @RequestParam Long userId) {
        boolean deleted = savedPlaceService.deleteSavedPlace(id, userId);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("message", "Removed from saved"));
    }
}
