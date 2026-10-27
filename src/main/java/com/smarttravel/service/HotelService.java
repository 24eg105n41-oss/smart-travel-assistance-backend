package com.smarttravel.service;

import com.smarttravel.dto.HotelDTO;
import com.smarttravel.entity.Hotel;
import com.smarttravel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public HotelDTO addHotel(HotelDTO dto) {
        Hotel hotel = mapToEntity(dto);
        Hotel saved = hotelRepository.save(hotel);
        return mapToDTO(saved);
    }

    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public HotelDTO getHotelById(Long id) {
        Optional<Hotel> opt = hotelRepository.findById(id);
        return opt.map(this::mapToDTO).orElse(null);
    }

    public List<HotelDTO> searchByName(String name) {
        return hotelRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<HotelDTO> filterByPrice(Double maxPrice) {
        return hotelRepository.findByPriceLessThanEqual(maxPrice)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<HotelDTO> filterByRating(Double minRating) {
        return hotelRepository.findByRatingGreaterThanEqual(minRating)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Mapping helpers
    private Hotel mapToEntity(HotelDTO dto) {
        Hotel h = new Hotel();
        h.setName(dto.getName());
        h.setDescription(dto.getDescription());
        h.setLocation(dto.getLocation());
        h.setPrice(dto.getPrice());
        h.setRating(dto.getRating());
        h.setLatitude(dto.getLatitude());
        h.setLongitude(dto.getLongitude());
        h.setImageUrl(dto.getImageUrl());
        return h;
    }

    private HotelDTO mapToDTO(Hotel h) {
        HotelDTO dto = new HotelDTO();
        dto.setId(h.getId());
        dto.setName(h.getName());
        dto.setDescription(h.getDescription());
        dto.setLocation(h.getLocation());
        dto.setPrice(h.getPrice());
        dto.setRating(h.getRating());
        dto.setLatitude(h.getLatitude());
        dto.setLongitude(h.getLongitude());
        dto.setImageUrl(h.getImageUrl());
        return dto;
    }
}
