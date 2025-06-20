package com.mp.parkinglot.repository;

import com.mp.parkinglot.entity.Review;
import com.mp.parkinglot.entity.ReviewUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewUserRepository extends JpaRepository<ReviewUser, Long> {
    Optional<ReviewUser> findById(Long id);
    List<ReviewUser> findByReviewId(String reviewId);
    List<ReviewUser> findByUserId(String userId);
    Optional<ReviewUser> findByUserIdAndReviewId(String userId, Long reviewId);
}
