package com.smarttravel.dto;

import com.smarttravel.entity.PlaceType;
import jakarta.validation.constraints.NotNull;

public class SavedPlaceDTO {
    private Long id;
    @NotNull
    private PlaceType placeType;
    private Long travelPlaceId;
    private Long restaurantId;
    private Long hotelId;
    private String notes;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public PlaceType getPlaceType() { return placeType; }
    public void setPlaceType(PlaceType placeType) { this.placeType = placeType; }
    public Long getTravelPlaceId() { return travelPlaceId; }
    public void setTravelPlaceId(Long travelPlaceId) { this.travelPlaceId = travelPlaceId; }
    public Long getRestaurantId() { return restaurantId; }
    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }
    public Long getHotelId() { return hotelId; }
    public void setHotelId(Long hotelId) { this.hotelId = hotelId; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
