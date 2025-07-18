package com.mp.parkinglot.repository;

import com.mp.parkinglot.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(Long reviewId);
    List<Review> findByUserId(String userId);
    List<Review> findByParkinglotId(String parkinglotId);
}
