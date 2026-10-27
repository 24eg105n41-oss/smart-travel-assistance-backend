package com.smarttravel.service;

import com.smarttravel.dto.TravelPlaceDTO;
import com.smarttravel.entity.TravelPlace;
import com.smarttravel.repository.TravelPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TravelPlaceService {

    @Autowired
    private TravelPlaceRepository travelPlaceRepository;

    public TravelPlaceDTO addPlace(TravelPlaceDTO dto) {
        TravelPlace place = mapToEntity(dto);
        TravelPlace saved = travelPlaceRepository.save(place);
        return mapToDTO(saved);
    }

    public List<TravelPlaceDTO> getAllPlaces() {
        return travelPlaceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TravelPlaceDTO getPlaceById(Long id) {
        Optional<TravelPlace> opt = travelPlaceRepository.findById(id);
        return opt.map(this::mapToDTO).orElse(null);
    }

    public List<TravelPlaceDTO> searchByName(String name) {
        return travelPlaceRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<TravelPlaceDTO> searchByCategory(String category) {
        return travelPlaceRepository.findByCategoryIgnoreCase(category)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Helper mapping methods
    private TravelPlace mapToEntity(TravelPlaceDTO dto) {
        TravelPlace place = new TravelPlace();
        place.setName(dto.getName());
        place.setDescription(dto.getDescription());
        place.setLocation(dto.getLocation());
        place.setCategory(dto.getCategory());
        place.setLatitude(dto.getLatitude());
        place.setLongitude(dto.getLongitude());
        place.setImageUrl(dto.getImageUrl());
        return place;
    }

    private TravelPlaceDTO mapToDTO(TravelPlace place) {
        TravelPlaceDTO dto = new TravelPlaceDTO();
        dto.setId(place.getId());
        dto.setName(place.getName());
        dto.setDescription(place.getDescription());
        dto.setLocation(place.getLocation());
        dto.setCategory(place.getCategory());
        dto.setLatitude(place.getLatitude());
        dto.setLongitude(place.getLongitude());
        dto.setImageUrl(place.getImageUrl());
        return dto;
    }
}
