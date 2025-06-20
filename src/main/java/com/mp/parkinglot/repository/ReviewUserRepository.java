package com.mp.parkinglot.repository;

import com.mp.parkinglot.entity.Review;
import com.mp.parkinglot.entity.ReviewUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewUserRepository extends JpaRepository<ReviewUser, Long> {
    Optional<ReviewUser> findById(Long id);
    Optional<ReviewUser> findByUserIdAndReviewId(String userId, Long reviewId);
}
