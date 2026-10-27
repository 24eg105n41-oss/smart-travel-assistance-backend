package com.smarttravel.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SavedPlace> savedPlaces = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmergencyContact> emergencyContacts = new HashSet<>();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Set<SavedPlace> getSavedPlaces() { return savedPlaces; }
    public void setSavedPlaces(Set<SavedPlace> savedPlaces) { this.savedPlaces = savedPlaces; }
    public Set<EmergencyContact> getEmergencyContacts() { return emergencyContacts; }
    public void setEmergencyContacts(Set<EmergencyContact> emergencyContacts) { this.emergencyContacts = emergencyContacts; }
}
