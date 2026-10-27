package com.smarttravel.dto;

import com.smarttravel.entity.PlaceType;

public class TripItemDTO {
    private Long id;
    private Integer dayNumber;
    private String timeSlot;
    private PlaceType itemType;
    private Long travelPlaceId;
    private String travelPlaceName;
    private Long restaurantId;
    private String restaurantName;
    private Long hotelId;
    private String hotelName;
    private String customTitle;
    private String customDescription;
    private Integer orderIndex;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getDayNumber() { return dayNumber; }
    public void setDayNumber(Integer dayNumber) { this.dayNumber = dayNumber; }
    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }
    public PlaceType getItemType() { return itemType; }
    public void setItemType(PlaceType itemType) { this.itemType = itemType; }
    public Long getTravelPlaceId() { return travelPlaceId; }
    public void setTravelPlaceId(Long travelPlaceId) { this.travelPlaceId = travelPlaceId; }
    public String getTravelPlaceName() { return travelPlaceName; }
    public void setTravelPlaceName(String travelPlaceName) { this.travelPlaceName = travelPlaceName; }
    public Long getRestaurantId() { return restaurantId; }
    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }
    public String getRestaurantName() { return restaurantName; }
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }
    public Long getHotelId() { return hotelId; }
    public void setHotelId(Long hotelId) { this.hotelId = hotelId; }
    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }
    public String getCustomTitle() { return customTitle; }
    public void setCustomTitle(String customTitle) { this.customTitle = customTitle; }
    public String getCustomDescription() { return customDescription; }
    public void setCustomDescription(String customDescription) { this.customDescription = customDescription; }
    public Integer getOrderIndex() { return orderIndex; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }
}
