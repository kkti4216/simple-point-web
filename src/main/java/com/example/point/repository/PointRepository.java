package com.example.point.repository;

import com.example.point.entity.PointTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PointRepository extends JpaRepository<PointTransaction, Long> {
    List<PointTransaction> findByUserIdOrderByCreatedAtDesc(String userId);
    
    @Query("SELECT COALESCE(SUM(CASE WHEN type = 'ADD' THEN points ELSE -points END), 0) FROM PointTransaction WHERE userId = ?1")
    Integer getPointBalance(String userId);
}