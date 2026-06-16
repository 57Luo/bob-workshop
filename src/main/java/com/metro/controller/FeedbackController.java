package com.metro.controller;

import com.metro.dto.FeedbackStatisticsDto;
import com.metro.model.Feedback;
import com.metro.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 回饋 REST API 控制器
 * 提供乘客回饋的 CRUD 操作和統計功能
 */
@RestController
@RequestMapping("/api/feedbacks")
@CrossOrigin(origins = "*")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * 取得所有回饋
     * GET /api/feedbacks
     */
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    /**
     * 取得單一回饋
     * GET /api/feedbacks/{id}
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
     * 依車站 ID 查詢回饋
     * GET /api/feedbacks/station/{stationId}
     */
    @GetMapping("/station/{stationId}")
    public ResponseEntity<List<Feedback>> getFeedbacksByStation(@PathVariable Long stationId) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByStationId(stationId);
        return ResponseEntity.ok(feedbacks);
    }

    /**
     * 新增回饋
     * POST /api/feedbacks
     */
    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        try {
            Feedback savedFeedback = feedbackService.createFeedback(feedback);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFeedback);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 刪除回饋
     * DELETE /api/feedbacks/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * 取得所有車站的統計資料
     * GET /api/feedbacks/statistics
     */
    @GetMapping("/statistics")
    public ResponseEntity<List<FeedbackStatisticsDto>> getAllStatistics() {
        List<FeedbackStatisticsDto> statistics = feedbackService.getAllStationsStatistics();
        return ResponseEntity.ok(statistics);
    }

    /**
     * 取得特定車站的統計資料
     * GET /api/feedbacks/statistics/{stationId}
     */
    @GetMapping("/statistics/{stationId}")
    public ResponseEntity<FeedbackStatisticsDto> getStationStatistics(@PathVariable Long stationId) {
        try {
            FeedbackStatisticsDto statistics = feedbackService.getStationStatistics(stationId);
            return ResponseEntity.ok(statistics);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 取得特定車站的平均評分
     * GET /api/feedbacks/average-rating/{stationId}
     */
    @GetMapping("/average-rating/{stationId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long stationId) {
        try {
            FeedbackStatisticsDto statistics = feedbackService.getStationStatistics(stationId);
            return ResponseEntity.ok(statistics.getAverageRating());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

// Made with Bob
