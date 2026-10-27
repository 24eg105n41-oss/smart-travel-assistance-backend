package com.smarttravel.service;

import com.smarttravel.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired private TravelPlaceService travelPlaceService;
    @Autowired private RestaurantService restaurantService;
    @Autowired private HotelService hotelService;

    public SearchResultDTO search(String query) {
        List<TravelPlaceDTO> places = travelPlaceService.searchByName(query);
        List<RestaurantDTO> restaurants = restaurantService.searchByName(query);
        List<HotelDTO> hotels = hotelService.searchByName(query);
        return new SearchResultDTO(places, restaurants, hotels);
    }
}
