package com.metro.repository;

import com.metro.model.Feedback;
import com.metro.model.FeedbackType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 回饋資料存取層
 * 
 * 提供回饋資料的 CRUD 操作和自訂查詢方法
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
    /**
     * 取得所有回饋（使用 JOIN FETCH 避免 N+1 查詢）
     *
     * @return 所有回饋列表
     */
    @Query("SELECT f FROM Feedback f JOIN FETCH f.station ORDER BY f.createdAt DESC")
    List<Feedback> findAllWithStation();
    
    /**
     * 根據車站 ID 查詢所有回饋
     * 
     * @param stationId 車站 ID
     * @return 該車站的所有回饋列表
     */
    @Query("SELECT f FROM Feedback f JOIN FETCH f.station WHERE f.station.id = :stationId")
    List<Feedback> findByStationId(@Param("stationId") Long stationId);
    
    /**
     * 根據車站 ID 查詢所有回饋，按建立時間降序排列
     *
     * @param stationId 車站 ID
     * @return 該車站的所有回饋列表（最新的在前）
     */
    @Query("SELECT f FROM Feedback f JOIN FETCH f.station WHERE f.station.id = :stationId ORDER BY f.createdAt DESC")
    List<Feedback> findByStationIdOrderByCreatedAtDesc(@Param("stationId") Long stationId);
    
    /**
     * 根據車站 ID 和評分查詢回饋
     * 
     * @param stationId 車站 ID
     * @param rating 評分（1-5）
     * @return 符合條件的回饋列表
     */
    List<Feedback> findByStationIdAndRating(Long stationId, Integer rating);
    
    /**
     * 根據回饋類型查詢所有回饋
     * 
     * @param type 回饋類型
     * @return 該類型的所有回饋列表
     */
    List<Feedback> findByType(FeedbackType type);
    
    /**
     * 根據車站 ID 和回饋類型查詢回饋
     * 
     * @param stationId 車站 ID
     * @param type 回饋類型
     * @return 符合條件的回饋列表
     */
    List<Feedback> findByStationIdAndType(Long stationId, FeedbackType type);
    
    /**
     * 計算車站的平均評分
     * 
     * @param stationId 車站 ID
     * @return 平均評分（如果沒有回饋則返回 null）
     */
    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.station.id = :stationId")
    Double calculateAverageRating(@Param("stationId") Long stationId);
    
    /**
     * 計算車站的回饋總數
     * 
     * @param stationId 車站 ID
     * @return 回饋總數
     */
    Long countByStationId(Long stationId);
    
    /**
     * 計算特定評分的回饋數量
     * 
     * @param stationId 車站 ID
     * @param rating 評分（1-5）
     * @return 該評分的回饋數量
     */
    Long countByStationIdAndRating(Long stationId, Integer rating);
    
    /**
     * 計算特定類型的回饋數量
     * 
     * @param stationId 車站 ID
     * @param type 回饋類型
     * @return 該類型的回饋數量
     */
    Long countByStationIdAndType(Long stationId, FeedbackType type);
    
    /**
     * 查詢評分高於指定值的回饋
     * 
     * @param stationId 車站 ID
     * @param minRating 最低評分
     * @return 符合條件的回饋列表
     */
    @Query("SELECT f FROM Feedback f WHERE f.station.id = :stationId AND f.rating >= :minRating ORDER BY f.createdAt DESC")
    List<Feedback> findHighRatedFeedbacks(@Param("stationId") Long stationId, @Param("minRating") Integer minRating);
    
    /**
     * 查詢最近的 N 筆回饋
     * 
     * @param stationId 車站 ID
     * @return 最近的回饋列表
     */
    @Query("SELECT f FROM Feedback f WHERE f.station.id = :stationId ORDER BY f.createdAt DESC")
    List<Feedback> findRecentFeedbacks(@Param("stationId") Long stationId);
}

// Made with Bob