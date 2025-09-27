package com.example.point.controller;

import com.example.point.entity.PointTransaction;
import com.example.point.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/points")
public class PointController {
    @Autowired
    private PointService pointService;
    
    @GetMapping("/{userId}")
    public Map<String, Integer> getBalance(@PathVariable String userId) {
        return Map.of("balance", pointService.getBalance(userId));
    }
    
    @PostMapping("/add")
    public ResponseEntity<String> addPoints(@RequestBody Map<String, Object> request) {
        String userId = (String) request.get("userId");
        Integer points = (Integer) request.get("points");
        String description = (String) request.get("description");
        
        pointService.addPoints(userId, points, description);
        return ResponseEntity.ok("ポイントを付与しました");
    }
    
    @PostMapping("/use")
    public ResponseEntity<String> usePoints(@RequestBody Map<String, Object> request) {
        String userId = (String) request.get("userId");
        Integer points = (Integer) request.get("points");
        String description = (String) request.get("description");
        
        if (pointService.usePoints(userId, points, description)) {
            return ResponseEntity.ok("ポイントを利用しました");
        } else {
            return ResponseEntity.badRequest().body("ポイント残高が不足しています");
        }
    }
    
    @GetMapping("/{userId}/history")
    public List<PointTransaction> getHistory(@PathVariable String userId) {
        return pointService.getHistory(userId);
    }
}