package com.smarttravel.config;

import com.smarttravel.entity.Hotel;
import com.smarttravel.entity.Restaurant;
import com.smarttravel.entity.TravelPlace;
import com.smarttravel.repository.HotelRepository;
import com.smarttravel.repository.RestaurantRepository;
import com.smarttravel.repository.TravelPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired private TravelPlaceRepository travelPlaceRepository;
    @Autowired private RestaurantRepository restaurantRepository;
    @Autowired private HotelRepository hotelRepository;

    @Override
    public void run(String... args) throws Exception {
        if (travelPlaceRepository.count() == 0) {
            seedTravelPlaces();
        }
        if (restaurantRepository.count() == 0) {
            seedRestaurants();
        }
        if (hotelRepository.count() == 0) {
            seedHotels();
        }
    }

    private void seedTravelPlaces() {
        travelPlaceRepository.saveAll(List.of(
            createPlace("Rushikonda Beach", "A pristine blue-flag beach in Visakhapatnam with golden sands and clear waters, popular for water sports and sunsets.", "Visakhapatnam, Andhra Pradesh", "Beach", 17.7833, 83.3712, "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=800&q=80"),
            createPlace("Araku Valley", "A scenic hill station surrounded by coffee plantations and tribal culture, about 120 km from Visakhapatnam.", "Araku Valley, Andhra Pradesh", "Nature", 18.3274, 82.8752, "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?auto=format&fit=crop&w=800&q=80"),
            createPlace("Borra Caves", "Million-year-old limestone caves with stunning stalactite and stalagmite formations in the Eastern Ghats.", "Araku Valley, Andhra Pradesh", "Historical", 18.1130, 83.0320, "https://images.unsplash.com/photo-1557400000-63f5f5ab68b2?auto=format&fit=crop&w=800&q=80"),
            createPlace("Kailasagiri", "A hilltop park in Visakhapatnam with a massive Shiva-Parvati statue and cable car rides with panoramic sea views.", "Visakhapatnam, Andhra Pradesh", "Park", 17.7459, 83.3570, "https://images.unsplash.com/photo-1506905925346-21bda4d32df4?auto=format&fit=crop&w=800&q=80"),
            createPlace("INS Kurusura Submarine Museum", "A decommissioned Indian Navy submarine turned museum on the Visakhapatnam beach road.", "Visakhapatnam, Andhra Pradesh", "Museum", 17.7200, 83.3400, "https://images.unsplash.com/photo-1587825140708-dfaf72ae4b04?auto=format&fit=crop&w=800&q=80"),
            createPlace("Charminar", "The iconic 16th-century mosque and monument in the heart of Hyderabad, symbol of the city.", "Hyderabad, Telangana", "Historical", 17.3616, 78.4747, "https://images.unsplash.com/photo-1578662996442-48f60103fc96?auto=format&fit=crop&w=800&q=80"),
            createPlace("Golconda Fort", "A magnificent 11th-century fortress with acoustic engineering, diamond history and panoramic city views.", "Hyderabad, Telangana", "Historical", 17.3833, 78.4011, "https://images.unsplash.com/photo-1566227591360-b5e70e01dce1?auto=format&fit=crop&w=800&q=80"),
            createPlace("Hussain Sagar Lake", "A large artificial lake in Hyderabad with a giant Buddha statue on a rock island in the center.", "Hyderabad, Telangana", "Nature", 17.4239, 78.4738, "https://images.unsplash.com/photo-1545569341-9eb8b30979d9?auto=format&fit=crop&w=800&q=80"),
            createPlace("Baga Beach", "A lively beach in North Goa known for water sports, beach shacks, nightlife and fresh seafood.", "Goa", "Beach", 15.5529, 73.7516, "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?auto=format&fit=crop&w=800&q=80"),
            createPlace("Hampi", "UNESCO World Heritage Site - ruins of the Vijayanagara Empire with stunning boulder landscapes and ancient temples.", "Karnataka", "Historical", 15.3350, 76.4600, "https://images.unsplash.com/photo-1582510003544-4d00b7f74220?auto=format&fit=crop&w=800&q=80"),
            createPlace("Valley of Flowers", "A national park in Uttarakhand famed for its meadows of endemic alpine flowers and diverse flora.", "Uttarakhand", "Nature", 30.7283, 79.6050, "https://images.unsplash.com/photo-1476514525535-07fb3b4ae5f1?auto=format&fit=crop&w=800&q=80"),
            createPlace("Marine Drive", "Mumbai's iconic seaside promenade offering stunning views of the Arabian Sea, especially at sunset.", "Mumbai, Maharashtra", "Nature", 18.9438, 72.8234, "https://images.unsplash.com/photo-1529253355930-ddbe423a2ac7?auto=format&fit=crop&w=800&q=80")
        ));
    }

    private void seedRestaurants() {
        restaurantRepository.saveAll(List.of(
            createRestaurant("Sea Side Spice", "Fresh seafood with ocean views, specializing in Andhra-style fish curry and prawns.", "Rushikonda, Visakhapatnam", "Seafood", 4.3, 17.7820, 83.3700, "https://images.unsplash.com/photo-1414235077428-338989a2e8c0?auto=format&fit=crop&w=800&q=80"),
            createRestaurant("Bamboo Kitchen", "Authentic tribal cuisine from the Araku valley, featuring bamboo chicken and local produce.", "Araku Valley, Andhra Pradesh", "Tribal", 4.5, 18.3260, 82.8740, "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?auto=format&fit=crop&w=800&q=80"),
            createRestaurant("Hotel Saarovar", "Classic Andhra meals served on banana leaf with unlimited servings of sambar and curries.", "Visakhapatnam, Andhra Pradesh", "South Indian", 4.2, 17.7231, 83.3012, "https://images.unsplash.com/photo-1567620905732-2d1ec7ab7445?auto=format&fit=crop&w=800&q=80"),
            createRestaurant("Paradise Biryani", "Hyderabad's legendary biryani house serving authentic Hyderabadi dum biryani since 1953.", "Secunderabad, Hyderabad", "Hyderabadi", 4.6, 17.4400, 78.4983, "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?auto=format&fit=crop&w=800&q=80"),
            createRestaurant("Fisherman's Wharf", "Goan seafood restaurant on a pontoon boat with freshly caught fish and kingfisher beer.", "Panaji, Goa", "Goan Seafood", 4.4, 15.4909, 73.8278, "https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=crop&w=800&q=80"),
            createRestaurant("Kamat Hotel", "Popular chain serving fresh South Indian breakfast - idli, vada, dosa with authentic chutneys.", "Visakhapatnam, Andhra Pradesh", "South Indian", 4.1, 17.7100, 83.2980, "https://images.unsplash.com/photo-1601050690597-df0568f70950?auto=format&fit=crop&w=800&q=80"),
            createRestaurant("Coco Shack", "Beachside shack in Goa with fresh coconut drinks, grilled fish and relaxed vibes.", "Anjuna, Goa", "Goan", 4.0, 15.5800, 73.7400, "https://images.unsplash.com/photo-1555396273-367ea4eb4db5?auto=format&fit=crop&w=800&q=80"),
            createRestaurant("Pista House", "Famous Hyderabadi sweet shop and restaurant known for Haleem, Sheer Korma and Qubani ka Meetha.", "Hyderabad, Telangana", "Hyderabadi", 4.3, 17.3850, 78.4763, "https://images.unsplash.com/photo-1549931319-a545dcf3bc73?auto=format&fit=crop&w=800&q=80")
        ));
    }

    private void seedHotels() {
        hotelRepository.saveAll(List.of(
            createHotel("The Park Visakhapatnam", "Beachfront luxury hotel with panoramic sea views, outdoor pool, spa and multiple dining options.", "Beach Road, Visakhapatnam", 4500.00, 4.4, 17.7215, 83.3391, "https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?auto=format&fit=crop&w=800&q=80"),
            createHotel("Novotel Visakhapatnam", "Modern 5-star hotel with convention facilities, rooftop pool and stunning Bay of Bengal views.", "Visakhapatnam, Andhra Pradesh", 6500.00, 4.5, 17.7312, 83.3210, "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?auto=format&fit=crop&w=800&q=80"),
            createHotel("Araku Jungle Retreat", "Eco-resort amid coffee plantations in Araku Valley with cottages, trekking and tribal experiences.", "Araku Valley, Andhra Pradesh", 3200.00, 4.3, 18.3290, 82.8760, "https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?auto=format&fit=crop&w=800&q=80"),
            createHotel("Taj Falaknuma Palace", "Opulent palace hotel in Hyderabad once owned by the Nizam, offering regal suites and butler service.", "Hyderabad, Telangana", 35000.00, 4.9, 17.3369, 78.4626, "https://images.unsplash.com/photo-1566073771259-6a8506099945?auto=format&fit=crop&w=800&q=80"),
            createHotel("ITC Kohenur Hyderabad", "Luxury hotel on the banks of Durgam Cheruvu lake with contemporary design and fine dining.", "Hyderabad, Telangana", 12000.00, 4.7, 17.4250, 78.3820, "https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?auto=format&fit=crop&w=800&q=80"),
            createHotel("W Goa", "Stylish beachfront resort in Vagator with modern design, DJ pool parties and Goa's best sunsets.", "Vagator, Goa", 14000.00, 4.6, 15.5994, 73.7448, "https://images.unsplash.com/photo-1571003123894-1f0594d2b5d9?auto=format&fit=crop&w=800&q=80"),
            createHotel("Grand Bay Hotel", "Mid-range comfortable hotel close to Rushikonda beach with clean rooms and good breakfast.", "Visakhapatnam, Andhra Pradesh", 2200.00, 3.9, 17.7800, 83.3680, "https://images.unsplash.com/photo-1455587734955-081b22074882?auto=format&fit=crop&w=800&q=80"),
            createHotel("Lemon Tree Hotel Goa", "Cheerful mid-range hotel in Calangute with pool, bar and easy beach access.", "Calangute, Goa", 4800.00, 4.1, 15.5440, 73.7543, "https://images.unsplash.com/photo-1444201983204-c43cbd584d93?auto=format&fit=crop&w=800&q=80")
        ));
    }

    private TravelPlace createPlace(String name, String desc, String loc, String cat, double lat, double lng, String img) {
        TravelPlace tp = new TravelPlace();
        tp.setName(name); tp.setDescription(desc); tp.setLocation(loc); tp.setCategory(cat);
        tp.setLatitude(lat); tp.setLongitude(lng); tp.setImageUrl(img);
        return tp;
    }

    private Restaurant createRestaurant(String name, String desc, String loc, String cuisine, double rating, double lat, double lng, String img) {
        Restaurant r = new Restaurant();
        r.setName(name); r.setDescription(desc); r.setLocation(loc); r.setCuisine(cuisine);
        r.setRating(rating); r.setLatitude(lat); r.setLongitude(lng); r.setImageUrl(img);
        return r;
    }

    private Hotel createHotel(String name, String desc, String loc, double price, double rating, double lat, double lng, String img) {
        Hotel h = new Hotel();
        h.setName(name); h.setDescription(desc); h.setLocation(loc); h.setPrice(price);
        h.setRating(rating); h.setLatitude(lat); h.setLongitude(lng); h.setImageUrl(img);
        return h;
    }
}
