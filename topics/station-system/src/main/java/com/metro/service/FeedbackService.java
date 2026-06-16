package com.metro.service;

import com.metro.dto.FeedbackStatisticsDto;
import com.metro.model.Feedback;
import com.metro.model.FeedbackType;
import com.metro.model.Station;
import com.metro.repository.FeedbackRepository;
import com.metro.repository.StationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 回饋服務層
 * 
 * 處理回饋相關的業務邏輯
 */
@Service
@Transactional
public class FeedbackService {
    
    private final FeedbackRepository feedbackRepository;
    private final StationRepository stationRepository;
    
    public FeedbackService(FeedbackRepository feedbackRepository, 
                          StationRepository stationRepository) {
        this.feedbackRepository = feedbackRepository;
        this.stationRepository = stationRepository;
    }
    
    /**
     * 建立新回饋
     * 
     * @param feedback 回饋物件
     * @return 建立的回饋
     * @throws RuntimeException 如果驗證失敗
     */
    public Feedback createFeedback(Feedback feedback) {
        // 驗證評分範圍（1-5）
        if (feedback.getRating() == null || feedback.getRating() < 1 || feedback.getRating() > 5) {
            throw new RuntimeException("評分必須在 1 到 5 之間");
        }
        
        // 驗證回饋內容
        if (feedback.getContent() == null || feedback.getContent().trim().isEmpty()) {
            throw new RuntimeException("回饋內容不可為空");
        }
        
        if (feedback.getContent().length() > 500) {
            throw new RuntimeException("回饋內容不可超過 500 字元");
        }
        
        // 驗證回饋類型
        if (feedback.getType() == null) {
            throw new RuntimeException("回饋類型不可為空");
        }
        
        // 驗證車站是否存在
        if (feedback.getStation() == null || feedback.getStation().getId() == null) {
            throw new RuntimeException("必須指定車站");
        }
        
        Station station = stationRepository.findById(feedback.getStation().getId())
                .orElseThrow(() -> new RuntimeException("車站不存在: ID = " + feedback.getStation().getId()));
        
        // 設定車站關聯
        feedback.setStation(station);
        
        // 儲存回饋
        return feedbackRepository.save(feedback);
    }
    
    /**
     * 取得所有回饋
     *
     * @return 所有回饋列表
     */
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAllWithStation();
    }
    
    /**
     * 根據 ID 取得回饋
     * 
     * @param id 回饋 ID
     * @return 回饋物件
     * @throws RuntimeException 如果回饋不存在
     */
    @SuppressWarnings("null")
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("回饋不存在: ID = " + id));
    }
    
    /**
     * 根據車站 ID 取得所有回饋
     * 
     * @param stationId 車站 ID
     * @return 該車站的所有回饋列表
     */
    public List<Feedback> getFeedbacksByStation(Long stationId) {
        // 驗證車站是否存在
        if (!stationRepository.existsById(stationId)) {
            throw new RuntimeException("車站不存在: ID = " + stationId);
        }
        return feedbackRepository.findByStationIdOrderByCreatedAtDesc(stationId);
    }
    
    /**
     * 根據回饋類型取得所有回饋
     * 
     * @param type 回饋類型
     * @return 該類型的所有回饋列表
     */
    public List<Feedback> getFeedbacksByType(FeedbackType type) {
        return feedbackRepository.findByType(type);
    }
    
    /**
     * 刪除回饋
     * 
     * @param id 回饋 ID
     * @throws RuntimeException 如果回饋不存在
     */
    @SuppressWarnings("null")
    public void deleteFeedback(Long id) {
        if (!feedbackRepository.existsById(id)) {
            throw new RuntimeException("回饋不存在: ID = " + id);
        }
        feedbackRepository.deleteById(id);
    }
    
    /**
     * 取得車站的回饋統計資訊
     * 
     * @param stationId 車站 ID
     * @return 統計資訊 DTO
     * @throws RuntimeException 如果車站不存在
     */
    @SuppressWarnings("null")
    public FeedbackStatisticsDto getStationStatistics(Long stationId) {
        // 驗證車站是否存在
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new RuntimeException("車站不存在: ID = " + stationId));
        
        // 建立統計 DTO
        FeedbackStatisticsDto stats = new FeedbackStatisticsDto(
                station.getId(),
                station.getCode(),
                station.getName()
        );
        
        // 計算平均評分
        Double avgRating = feedbackRepository.calculateAverageRating(stationId);
        stats.setAverageRating(avgRating != null ? Math.round(avgRating * 10.0) / 10.0 : 0.0);
        
        // 計算回饋總數
        Long totalCount = feedbackRepository.countByStationId(stationId);
        stats.setTotalFeedbacks(totalCount);
        
        // 計算各類型的回饋數量
        for (FeedbackType type : FeedbackType.values()) {
            Long count = feedbackRepository.countByStationIdAndType(stationId, type);
            stats.addTypeCount(type, count);
        }
        
        // 計算各評分的回饋數量
        for (int rating = 1; rating <= 5; rating++) {
            Long count = feedbackRepository.countByStationIdAndRating(stationId, rating);
            stats.addRatingCount(rating, count);
        }
        
        return stats;
    }
    
    /**
     * 取得高評分回饋（4 星以上）
     * 
     * @param stationId 車站 ID
     * @return 高評分回饋列表
     */
    public List<Feedback> getHighRatedFeedbacks(Long stationId) {
        return feedbackRepository.findHighRatedFeedbacks(stationId, 4);
    }
    
    /**
     * 取得最近的回饋
     * 
     * @param stationId 車站 ID
     * @return 最近的回饋列表
     */
    public List<Feedback> getRecentFeedbacks(Long stationId) {
        return feedbackRepository.findRecentFeedbacks(stationId);
    }
}

// Made with Bob