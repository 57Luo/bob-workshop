package com.metro.controller;

import com.metro.dto.FeedbackStatisticsDto;
import com.metro.model.Feedback;
import com.metro.model.FeedbackType;
import com.metro.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 回饋 REST API 控制器
 * 
 * 提供回饋管理的 RESTful API
 */
@RestController
@RequestMapping("/api/feedbacks")
@CrossOrigin(origins = "*")
public class FeedbackController {
    
    private final FeedbackService feedbackService;
    
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
    
    /**
     * 取得所有回饋
     * 
     * @return 所有回饋列表
     */
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }
    
    /**
     * 根據 ID 取得回饋
     * 
     * @param id 回饋 ID
     * @return 回饋資料
     */
    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        try {
            Feedback feedback = feedbackService.getFeedbackById(id);
            return ResponseEntity.ok(feedback);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * 建立新回饋
     * 
     * @param feedback 回饋資料
     * @return 建立的回饋
     */
    @PostMapping
    public ResponseEntity<?> createFeedback(@RequestBody Feedback feedback) {
        try {
            Feedback createdFeedback = feedbackService.createFeedback(feedback);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFeedback);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * 根據車站 ID 取得所有回饋
     * 
     * @param stationId 車站 ID
     * @return 該車站的所有回饋列表
     */
    @GetMapping("/station/{stationId}")
    public ResponseEntity<?> getFeedbacksByStation(@PathVariable Long stationId) {
        try {
            List<Feedback> feedbacks = feedbackService.getFeedbacksByStation(stationId);
            return ResponseEntity.ok(feedbacks);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * 取得車站的回饋統計資訊
     * 
     * @param stationId 車站 ID
     * @return 統計資訊
     */
    @GetMapping("/station/{stationId}/statistics")
    public ResponseEntity<?> getStationStatistics(@PathVariable Long stationId) {
        try {
            FeedbackStatisticsDto statistics = feedbackService.getStationStatistics(stationId);
            return ResponseEntity.ok(statistics);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * 根據回饋類型取得所有回饋
     * 
     * @param type 回饋類型
     * @return 該類型的所有回饋列表
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<?> getFeedbacksByType(@PathVariable String type) {
        try {
            FeedbackType feedbackType = FeedbackType.valueOf(type.toUpperCase());
            List<Feedback> feedbacks = feedbackService.getFeedbacksByType(feedbackType);
            return ResponseEntity.ok(feedbacks);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("無效的回饋類型: " + type);
        }
    }
    
    /**
     * 取得高評分回饋（4 星以上）
     * 
     * @param stationId 車站 ID
     * @return 高評分回饋列表
     */
    @GetMapping("/station/{stationId}/high-rated")
    public ResponseEntity<?> getHighRatedFeedbacks(@PathVariable Long stationId) {
        try {
            List<Feedback> feedbacks = feedbackService.getHighRatedFeedbacks(stationId);
            return ResponseEntity.ok(feedbacks);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * 取得最近的回饋
     * 
     * @param stationId 車站 ID
     * @return 最近的回饋列表
     */
    @GetMapping("/station/{stationId}/recent")
    public ResponseEntity<?> getRecentFeedbacks(@PathVariable Long stationId) {
        try {
            List<Feedback> feedbacks = feedbackService.getRecentFeedbacks(stationId);
            return ResponseEntity.ok(feedbacks);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * 刪除回饋
     * 
     * @param id 回饋 ID
     * @return 無內容回應
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long id) {
        try {
            feedbackService.deleteFeedback(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

// Made with Bob