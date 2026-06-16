package com.metro.service;

import com.metro.dto.FeedbackStatisticsDto;
import com.metro.model.Feedback;
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
    
    public FeedbackService(FeedbackRepository feedbackRepository, StationRepository stationRepository) {
        this.feedbackRepository = feedbackRepository;
        this.stationRepository = stationRepository;
    }
    
    /**
     * 取得所有回饋
     * 
     * @return 所有回饋列表
     */
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
    
    /**
     * 根據 ID 取得回饋
     * 
     * @param id 回饋 ID
     * @return 回饋資料
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
     * @return 該車站的所有回饋
     */
    public List<Feedback> getFeedbacksByStationId(Long stationId) {
        return feedbackRepository.findByStationId(stationId);
    }
    
    /**
     * 建立新回饋
     * 
     * @param feedback 回饋資料
     * @return 建立的回饋
     */
    @SuppressWarnings("null")
    public Feedback createFeedback(Feedback feedback) {
        // 驗證評分範圍
        if (feedback.getRating() < 1 || feedback.getRating() > 5) {
            throw new RuntimeException("評分必須在 1-5 之間");
        }
        
        // 驗證車站是否存在
        if (feedback.getStation() == null || feedback.getStation().getId() == null) {
            throw new RuntimeException("必須指定車站");
        }
        
        Station station = stationRepository.findById(feedback.getStation().getId())
                .orElseThrow(() -> new RuntimeException("車站不存在: ID = " + feedback.getStation().getId()));
        
        feedback.setStation(station);
        return feedbackRepository.save(feedback);
    }
    
    /**
     * 刪除回饋
     * 
     * @param id 回饋 ID
     */
    @SuppressWarnings("null")
    public void deleteFeedback(Long id) {
        if (!feedbackRepository.existsById(id)) {
            throw new RuntimeException("回饋不存在: ID = " + id);
        }
        feedbackRepository.deleteById(id);
    }
    
    /**
     * 取得車站的回饋統計
     * 
     * @param stationId 車站 ID
     * @return 回饋統計資料
     */
    @SuppressWarnings("null")
    public FeedbackStatisticsDto getStationStatistics(Long stationId) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new RuntimeException("車站不存在: ID = " + stationId));
        
        Long totalFeedbacks = feedbackRepository.countByStationId(stationId);
        Double averageRating = feedbackRepository.calculateAverageRatingByStationId(stationId);
        
        // 如果沒有回饋,平均評分設為 0
        if (averageRating == null) {
            averageRating = 0.0;
        }
        
        return new FeedbackStatisticsDto(
                station.getId(),
                station.getName(),
                station.getCode(),
                totalFeedbacks,
                averageRating
        );
    }
    
    /**
     * 取得所有車站的回饋統計
     * 
     * @return 所有車站的統計列表
     */
    public List<FeedbackStatisticsDto> getAllStationsStatistics() {
        List<Station> stations = stationRepository.findAll();
        return stations.stream()
                .map(station -> getStationStatistics(station.getId()))
                .toList();
    }
    
    /**
     * 根據評分篩選回饋
     * 
     * @param minRating 最低評分
     * @return 符合條件的回饋
     */
    public List<Feedback> getFeedbacksByMinRating(Integer minRating) {
        if (minRating < 1 || minRating > 5) {
            throw new RuntimeException("評分必須在 1-5 之間");
        }
        return feedbackRepository.findByRatingGreaterThanEqual(minRating);
    }
}

// Made with Bob