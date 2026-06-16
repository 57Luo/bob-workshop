package com.metro.repository;

import com.metro.model.Feedback;
import com.metro.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 回饋資料存取層
 * 
 * 提供回饋資料的資料庫操作
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
    /**
     * 根據車站查詢所有回饋
     * 
     * @param station 車站
     * @return 該車站的所有回饋
     */
    List<Feedback> findByStation(Station station);
    
    /**
     * 根據車站 ID 查詢所有回饋
     * 
     * @param stationId 車站 ID
     * @return 該車站的所有回饋
     */
    List<Feedback> findByStationId(Long stationId);
    
    /**
     * 根據評分查詢回饋
     * 
     * @param rating 評分
     * @return 該評分的所有回饋
     */
    List<Feedback> findByRating(Integer rating);
    
    /**
     * 查詢評分大於等於指定值的回饋
     * 
     * @param rating 最低評分
     * @return 符合條件的回饋
     */
    List<Feedback> findByRatingGreaterThanEqual(Integer rating);
    
    /**
     * 計算車站的平均評分
     * 
     * @param stationId 車站 ID
     * @return 平均評分
     */
    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.station.id = :stationId")
    Double calculateAverageRatingByStationId(Long stationId);
    
    /**
     * 計算車站的回饋數量
     * 
     * @param stationId 車站 ID
     * @return 回饋數量
     */
    Long countByStationId(Long stationId);
}

// Made with Bob