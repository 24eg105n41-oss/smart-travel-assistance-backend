package com.smarttravel.service;

import com.smarttravel.dto.SavedPlaceDTO;
import com.smarttravel.entity.*;
import com.smarttravel.repository.SavedPlaceRepository;
import com.smarttravel.repository.UserRepository;
import com.smarttravel.repository.TravelPlaceRepository;
import com.smarttravel.repository.RestaurantRepository;
import com.smarttravel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SavedPlaceService {

    @Autowired
    private SavedPlaceRepository savedPlaceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TravelPlaceRepository travelPlaceRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private HotelRepository hotelRepository;

    public SavedPlaceDTO savePlace(SavedPlaceDTO dto, Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) return null;
        SavedPlace savedPlace = new SavedPlace();
        savedPlace.setUser(userOpt.get());
        savedPlace.setPlaceType(dto.getPlaceType());
        // associate appropriate entity based on type
        switch (dto.getPlaceType()) {
            case TRAVEL_PLACE:
                if (dto.getTravelPlaceId() != null) {
                    travelPlaceRepository.findById(dto.getTravelPlaceId())
                            .ifPresent(savedPlace::setTravelPlace);
                }
                break;
            case RESTAURANT:
                if (dto.getRestaurantId() != null) {
                    restaurantRepository.findById(dto.getRestaurantId())
                            .ifPresent(savedPlace::setRestaurant);
                }
                break;
            case HOTEL:
                if (dto.getHotelId() != null) {
                    hotelRepository.findById(dto.getHotelId())
                            .ifPresent(savedPlace::setHotel);
                }
                break;
        }
        savedPlace.setNotes(dto.getNotes());
        SavedPlace saved = savedPlaceRepository.save(savedPlace);
        return mapToDTO(saved);
    }

    public List<SavedPlaceDTO> getSavedPlacesByUser(Long userId) {
        return savedPlaceRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public boolean deleteSavedPlace(Long id, Long userId) {
        Optional<SavedPlace> opt = savedPlaceRepository.findById(id);
        if (opt.isPresent() && opt.get().getUser().getId().equals(userId)) {
            savedPlaceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Mapping helpers
    private SavedPlaceDTO mapToDTO(SavedPlace sp) {
        SavedPlaceDTO dto = new SavedPlaceDTO();
        dto.setId(sp.getId());
        dto.setPlaceType(sp.getPlaceType());
        if (sp.getTravelPlace() != null) dto.setTravelPlaceId(sp.getTravelPlace().getId());
        if (sp.getRestaurant() != null) dto.setRestaurantId(sp.getRestaurant().getId());
        if (sp.getHotel() != null) dto.setHotelId(sp.getHotel().getId());
        dto.setNotes(sp.getNotes());
        return dto;
    }
}
