package com.metro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 車站實體
 *
 * 代表捷運的一個車站
 */
@Entity
@Table(name = "station")
public class Station {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 10)
    private String code;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(nullable = false, length = 20)
    private String line;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * 車站的所有回饋
     * 使用一對多關係，一個車站可以有多個回饋
     * @JsonIgnore 避免 JSON 序列化時的循環引用
     */
    @JsonIgnore
    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks = new ArrayList<>();
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Constructors
    public Station() {
    }
    
    public Station(String code, String name, String line) {
        this.code = code;
        this.name = name;
        this.line = line;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLine() {
        return line;
    }
    
    public void setLine(String line) {
        this.line = line;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }
    
    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
    
    /**
     * 新增回饋到車站
     *
     * @param feedback 回饋物件
     */
    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
        feedback.setStation(this);
    }
    
    /**
     * 從車站移除回饋
     *
     * @param feedback 回饋物件
     */
    public void removeFeedback(Feedback feedback) {
        feedbacks.remove(feedback);
        feedback.setStation(null);
    }
    
    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", line='" + line + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

// Made with Bob
