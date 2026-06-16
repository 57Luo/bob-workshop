package com.metro.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 乘客回饋實體
 *
 * 代表乘客對車站的評價與回饋
 */
@Entity
@Table(name = "feedback")
public class Feedback {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;
    
    @Column(nullable = false)
    private Integer rating; // 1-5 星評分
    
    @Column(length = 1000)
    private String comment;
    
    @Column(name = "passenger_name", length = 100)
    private String passengerName;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public Feedback() {
    }
    
    public Feedback(Station station, Integer rating, String comment, String passengerName) {
        this.station = station;
        this.rating = rating;
        this.comment = comment;
        this.passengerName = passengerName;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Station getStation() {
        return station;
    }
    
    public void setStation(Station station) {
        this.station = station;
    }
    
    public Integer getRating() {
        return rating;
    }
    
    public void setRating(Integer rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("評分必須在 1-5 之間");
        }
        this.rating = rating;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getPassengerName() {
        return passengerName;
    }
    
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", stationId=" + (station != null ? station.getId() : null) +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

// Made with Bob