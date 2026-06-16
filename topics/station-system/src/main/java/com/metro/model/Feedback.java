package com.metro.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 乘客回饋實體
 * 
 * 代表乘客針對特定車站提交的回饋意見
 */
@Entity
@Table(name = "feedback")
public class Feedback {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * 關聯的車站
     * 使用多對一關係，多個回饋可以對應一個車站
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;
    
    /**
     * 乘客姓名（可選，支援匿名回饋）
     */
    @Column(name = "passenger_name", length = 50)
    private String passengerName;
    
    /**
     * 評分（1-5 星）
     */
    @Column(nullable = false)
    private Integer rating;
    
    /**
     * 回饋類型
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private FeedbackType type;
    
    /**
     * 回饋內容
     */
    @Column(nullable = false, length = 500)
    private String content;
    
    /**
     * 建立時間
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * 在持久化之前自動設定建立時間
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    
    /**
     * 預設建構子（JPA 需要）
     */
    public Feedback() {
    }
    
    /**
     * 完整建構子
     * 
     * @param station 車站
     * @param passengerName 乘客姓名
     * @param rating 評分
     * @param type 回饋類型
     * @param content 回饋內容
     */
    public Feedback(Station station, String passengerName, Integer rating, 
                    FeedbackType type, String content) {
        this.station = station;
        this.passengerName = passengerName;
        this.rating = rating;
        this.type = type;
        this.content = content;
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
    
    public String getPassengerName() {
        return passengerName;
    }
    
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
    
    public Integer getRating() {
        return rating;
    }
    
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    public FeedbackType getType() {
        return type;
    }
    
    public void setType(FeedbackType type) {
        this.type = type;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
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
                ", passengerName='" + passengerName + '\'' +
                ", rating=" + rating +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

// Made with Bob