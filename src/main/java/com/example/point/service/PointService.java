package com.example.point.service;

import com.example.point.entity.PointTransaction;
import com.example.point.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {
    @Autowired
    private PointRepository pointRepository;
    
    public Integer getBalance(String userId) {
        return pointRepository.getPointBalance(userId);
    }
    
    public void addPoints(String userId, Integer points, String description) {
        pointRepository.save(new PointTransaction(userId, points, "ADD", description));
    }
    
    public boolean usePoints(String userId, Integer points, String description) {
        Integer balance = getBalance(userId);
        if (balance >= points) {
            pointRepository.save(new PointTransaction(userId, points, "USE", description));
            return true;
        }
        return false;
    }
    
    public List<PointTransaction> getHistory(String userId) {
        return pointRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
}