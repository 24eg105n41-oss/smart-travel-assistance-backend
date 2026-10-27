package com.smarttravel.service;

import com.smarttravel.dto.RestaurantDTO;
import com.smarttravel.entity.Restaurant;
import com.smarttravel.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public RestaurantDTO addRestaurant(RestaurantDTO dto) {
        Restaurant restaurant = mapToEntity(dto);
        Restaurant saved = restaurantRepository.save(restaurant);
        return mapToDTO(saved);
    }

    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public RestaurantDTO getRestaurantById(Long id) {
        Optional<Restaurant> opt = restaurantRepository.findById(id);
        return opt.map(this::mapToDTO).orElse(null);
    }

    public List<RestaurantDTO> searchByName(String name) {
        return restaurantRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<RestaurantDTO> filterByCuisine(String cuisine) {
        return restaurantRepository.findByCuisineIgnoreCase(cuisine)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Mapping helpers
    private Restaurant mapToEntity(RestaurantDTO dto) {
        Restaurant r = new Restaurant();
        r.setName(dto.getName());
        r.setDescription(dto.getDescription());
        r.setLocation(dto.getLocation());
        r.setCuisine(dto.getCuisine());
        r.setRating(dto.getRating());
        r.setLatitude(dto.getLatitude());
        r.setLongitude(dto.getLongitude());
        r.setImageUrl(dto.getImageUrl());
        return r;
    }

    private RestaurantDTO mapToDTO(Restaurant r) {
        RestaurantDTO dto = new RestaurantDTO();
        dto.setId(r.getId());
        dto.setName(r.getName());
        dto.setDescription(r.getDescription());
        dto.setLocation(r.getLocation());
        dto.setCuisine(r.getCuisine());
        dto.setRating(r.getRating());
        dto.setLatitude(r.getLatitude());
        dto.setLongitude(r.getLongitude());
        dto.setImageUrl(r.getImageUrl());
        return dto;
    }
}
