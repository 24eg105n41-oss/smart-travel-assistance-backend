package com.smarttravel.dto;

import java.util.List;

public class SearchResultDTO {
    private List<TravelPlaceDTO> places;
    private List<RestaurantDTO> restaurants;
    private List<HotelDTO> hotels;

    public SearchResultDTO() {}

    public SearchResultDTO(List<TravelPlaceDTO> places, List<RestaurantDTO> restaurants, List<HotelDTO> hotels) {
        this.places = places;
        this.restaurants = restaurants;
        this.hotels = hotels;
    }

    public List<TravelPlaceDTO> getPlaces() { return places; }
    public void setPlaces(List<TravelPlaceDTO> places) { this.places = places; }
    public List<RestaurantDTO> getRestaurants() { return restaurants; }
    public void setRestaurants(List<RestaurantDTO> restaurants) { this.restaurants = restaurants; }
    public List<HotelDTO> getHotels() { return hotels; }
    public void setHotels(List<HotelDTO> hotels) { this.hotels = hotels; }
}
