package com.metro.dto;

import com.metro.model.FeedbackType;

import java.util.HashMap;
import java.util.Map;

/**
 * 回饋統計資料傳輸物件
 * 
 * 用於傳遞車站回饋的統計資訊
 */
public class FeedbackStatisticsDto {
    
    private Long stationId;
    private String stationCode;
    private String stationName;
    private Double averageRating;
    private Long totalFeedbacks;
    private Map<FeedbackType, Long> feedbacksByType;
    private Map<Integer, Long> feedbacksByRating;
    
    // Constructors
    
    public FeedbackStatisticsDto() {
        this.feedbacksByType = new HashMap<>();
        this.feedbacksByRating = new HashMap<>();
    }
    
    public FeedbackStatisticsDto(Long stationId, String stationCode, String stationName) {
        this();
        this.stationId = stationId;
        this.stationCode = stationCode;
        this.stationName = stationName;
    }
    
    // Getters and Setters
    
    public Long getStationId() {
        return stationId;
    }
    
    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }
    
    public String getStationCode() {
        return stationCode;
    }
    
    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }
    
    public String getStationName() {
        return stationName;
    }
    
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
    
    public Double getAverageRating() {
        return averageRating;
    }
    
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
    
    public Long getTotalFeedbacks() {
        return totalFeedbacks;
    }
    
    public void setTotalFeedbacks(Long totalFeedbacks) {
        this.totalFeedbacks = totalFeedbacks;
    }
    
    public Map<FeedbackType, Long> getFeedbacksByType() {
        return feedbacksByType;
    }
    
    public void setFeedbacksByType(Map<FeedbackType, Long> feedbacksByType) {
        this.feedbacksByType = feedbacksByType;
    }
    
    public Map<Integer, Long> getFeedbacksByRating() {
        return feedbacksByRating;
    }
    
    public void setFeedbacksByRating(Map<Integer, Long> feedbacksByRating) {
        this.feedbacksByRating = feedbacksByRating;
    }
    
    // Helper methods
    
    /**
     * 新增特定類型的回饋數量
     * 
     * @param type 回饋類型
     * @param count 數量
     */
    public void addTypeCount(FeedbackType type, Long count) {
        this.feedbacksByType.put(type, count);
    }
    
    /**
     * 新增特定評分的回饋數量
     * 
     * @param rating 評分（1-5）
     * @param count 數量
     */
    public void addRatingCount(Integer rating, Long count) {
        this.feedbacksByRating.put(rating, count);
    }
    
    @Override
    public String toString() {
        return "FeedbackStatisticsDto{" +
                "stationId=" + stationId +
                ", stationCode='" + stationCode + '\'' +
                ", stationName='" + stationName + '\'' +
                ", averageRating=" + averageRating +
                ", totalFeedbacks=" + totalFeedbacks +
                ", feedbacksByType=" + feedbacksByType +
                ", feedbacksByRating=" + feedbacksByRating +
                '}';
    }
}

// Made with Bob