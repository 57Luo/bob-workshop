package com.metro.dto;

/**
 * 回饋統計資料傳輸物件
 * 
 * 用於傳遞車站的回饋統計資訊
 */
public class FeedbackStatisticsDto {
    
    private Long stationId;
    private String stationName;
    private String stationCode;
    private Long totalFeedbacks;
    private Double averageRating;
    
    // Constructors
    public FeedbackStatisticsDto() {
    }
    
    public FeedbackStatisticsDto(Long stationId, String stationName, String stationCode, 
                                 Long totalFeedbacks, Double averageRating) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.totalFeedbacks = totalFeedbacks;
        this.averageRating = averageRating;
    }
    
    // Getters and Setters
    public Long getStationId() {
        return stationId;
    }
    
    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }
    
    public String getStationName() {
        return stationName;
    }
    
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
    
    public String getStationCode() {
        return stationCode;
    }
    
    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }
    
    public Long getTotalFeedbacks() {
        return totalFeedbacks;
    }
    
    public void setTotalFeedbacks(Long totalFeedbacks) {
        this.totalFeedbacks = totalFeedbacks;
    }
    
    public Double getAverageRating() {
        return averageRating;
    }
    
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
    
    @Override
    public String toString() {
        return "FeedbackStatisticsDto{" +
                "stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                ", stationCode='" + stationCode + '\'' +
                ", totalFeedbacks=" + totalFeedbacks +
                ", averageRating=" + averageRating +
                '}';
    }
}

// Made with Bob