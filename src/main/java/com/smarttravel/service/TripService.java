package com.smarttravel.service;

import com.smarttravel.dto.TripDTO;
import com.smarttravel.dto.TripItemDTO;
import com.smarttravel.entity.*;
import com.smarttravel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripService {

    @Autowired private TripRepository tripRepository;
    @Autowired private TripItemRepository tripItemRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private TravelPlaceRepository travelPlaceRepository;
    @Autowired private RestaurantRepository restaurantRepository;
    @Autowired private HotelRepository hotelRepository;

    @Transactional
    public TripDTO createTrip(TripDTO dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Trip trip = new Trip();
        trip.setUser(user);
        trip.setName(dto.getName());
        trip.setDestination(dto.getDestination());
        trip.setStartDate(dto.getStartDate());
        trip.setEndDate(dto.getEndDate());
        trip.setNotes(dto.getNotes());

        Trip saved = tripRepository.save(trip);

        if (dto.getItems() != null) {
            for (TripItemDTO itemDto : dto.getItems()) {
                TripItem item = mapItemToEntity(itemDto, saved);
                tripItemRepository.save(item);
            }
        }

        return mapToDTO(tripRepository.findById(saved.getId()).orElse(saved));
    }

    public List<TripDTO> getTripsByUser(Long userId) {
        return tripRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public TripDTO getTripById(Long id, Long userId) {
        Trip trip = tripRepository.findById(id).orElse(null);
        if (trip == null || !trip.getUser().getId().equals(userId)) return null;
        return mapToDTO(trip);
    }

    @Transactional
    public boolean deleteTrip(Long id, Long userId) {
        Trip trip = tripRepository.findById(id).orElse(null);
        if (trip != null && trip.getUser().getId().equals(userId)) {
            tripRepository.delete(trip);
            return true;
        }
        return false;
    }

    @Transactional
    public TripItemDTO addTripItem(Long tripId, TripItemDTO dto, Long userId) {
        Trip trip = tripRepository.findById(tripId).orElse(null);
        if (trip == null || !trip.getUser().getId().equals(userId)) return null;
        TripItem item = mapItemToEntity(dto, trip);
        TripItem saved = tripItemRepository.save(item);
        return mapItemToDTO(saved);
    }

    @Transactional
    public boolean removeTripItem(Long itemId, Long userId) {
        TripItem item = tripItemRepository.findById(itemId).orElse(null);
        if (item != null && item.getTrip().getUser().getId().equals(userId)) {
            tripItemRepository.delete(item);
            return true;
        }
        return false;
    }

    private TripItem mapItemToEntity(TripItemDTO dto, Trip trip) {
        TripItem item = new TripItem();
        item.setTrip(trip);
        item.setDayNumber(dto.getDayNumber());
        item.setTimeSlot(dto.getTimeSlot());
        item.setItemType(dto.getItemType());
        item.setCustomTitle(dto.getCustomTitle());
        item.setCustomDescription(dto.getCustomDescription());
        item.setOrderIndex(dto.getOrderIndex());
        if (dto.getTravelPlaceId() != null) {
            travelPlaceRepository.findById(dto.getTravelPlaceId()).ifPresent(item::setTravelPlace);
        }
        if (dto.getRestaurantId() != null) {
            restaurantRepository.findById(dto.getRestaurantId()).ifPresent(item::setRestaurant);
        }
        if (dto.getHotelId() != null) {
            hotelRepository.findById(dto.getHotelId()).ifPresent(item::setHotel);
        }
        return item;
    }

    private TripDTO mapToDTO(Trip trip) {
        TripDTO dto = new TripDTO();
        dto.setId(trip.getId());
        dto.setName(trip.getName());
        dto.setDestination(trip.getDestination());
        dto.setStartDate(trip.getStartDate());
        dto.setEndDate(trip.getEndDate());
        dto.setNotes(trip.getNotes());
        dto.setCreatedAt(trip.getCreatedAt());
        if (trip.getItems() != null) {
            dto.setItems(trip.getItems().stream().map(this::mapItemToDTO).collect(Collectors.toList()));
        } else {
            dto.setItems(new ArrayList<>());
        }
        return dto;
    }

    private TripItemDTO mapItemToDTO(TripItem item) {
        TripItemDTO dto = new TripItemDTO();
        dto.setId(item.getId());
        dto.setDayNumber(item.getDayNumber());
        dto.setTimeSlot(item.getTimeSlot());
        dto.setItemType(item.getItemType());
        dto.setCustomTitle(item.getCustomTitle());
        dto.setCustomDescription(item.getCustomDescription());
        dto.setOrderIndex(item.getOrderIndex());
        if (item.getTravelPlace() != null) {
            dto.setTravelPlaceId(item.getTravelPlace().getId());
            dto.setTravelPlaceName(item.getTravelPlace().getName());
        }
        if (item.getRestaurant() != null) {
            dto.setRestaurantId(item.getRestaurant().getId());
            dto.setRestaurantName(item.getRestaurant().getName());
        }
        if (item.getHotel() != null) {
            dto.setHotelId(item.getHotel().getId());
            dto.setHotelName(item.getHotel().getName());
        }
        return dto;
    }
}
