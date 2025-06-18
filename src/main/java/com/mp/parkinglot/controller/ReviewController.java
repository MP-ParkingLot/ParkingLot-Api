package com.mp.parkinglot.controller;

import com.mp.parkinglot.dto.LikeRequest;
import com.mp.parkinglot.dto.ReviewRequest;
import com.mp.parkinglot.entity.Review;
import com.mp.parkinglot.entity.User;
import com.mp.parkinglot.repository.ReviewRepository;
import com.mp.parkinglot.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@Slf4j
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final AuthService authService;

    @GetMapping("/{location_id}")
    public List<Review> getReviews(@PathVariable("location_id") String locationId) {
        log.info("Get reviews for {}", locationId);
        return reviewRepository.findByParkinglotId(locationId);
    }

    @PostMapping("/{location_id}")
    public ResponseEntity<Map<String,String>> saveReview(@PathVariable("location_id") String locationId, @RequestBody ReviewRequest reviewDto, @CookieValue("accessToken") String accessToken) {
        log.info("Save review for {}", locationId);
        User user = authService.getUser(accessToken);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message","Login Required"));
        }

        Review review = reviewDto.toReview();
        review.setParkinglotId(locationId);
        review.setLikes(0);
        review.setCreatedAt(LocalDateTime.now());
        review.setUser(user);
        reviewRepository.save(review);

        return ResponseEntity.ok(Map.of("message", "Review Update Success"));
    }

    @PutMapping("/{review_id}")
    public ResponseEntity<Map<String,String>> updateReview(@PathVariable("review_id") Long reviewId, @RequestBody ReviewRequest reviewDto, @CookieValue("accessToken") String accessToken) {
        log.info("Update review for {}", reviewId);
        User user = authService.getUser(accessToken);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message","Login Required"));
        }

        Review review = reviewRepository.findById(reviewId).orElse(null);

        if (review == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","Review not existing"));
        }

        if (review.getUser().getId() != user.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message","Not allowed to update this review"));
        }

        review.setTitle(reviewDto.getTitle());
        review.setContents(reviewDto.getContents());
        review.setRate(reviewDto.getRate());
        review.setBathroom(reviewDto.getCategories().getBathroom());
        review.setWide(reviewDto.getCategories().getWide());
        review.setCharger(reviewDto.getCategories().getCharger());
        reviewRepository.save(review);

        return ResponseEntity.ok(Map.of("message", "Review Update Success"));
    }

    @DeleteMapping("/{review_id}")
    public ResponseEntity<Map<String,String>> deleteReview(@PathVariable("review_id") Long reviewId, @CookieValue("accessToken") String accessToken) {
        log.info("Delete review for {}", reviewId);
        User user = authService.getUser(accessToken);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message","Login Required"));
        }

        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","Review not existing"));
        }

        if (review.getUser().getId() != user.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message","Not allowed to delete this review"));
        }

        reviewRepository.delete(review);

        return ResponseEntity.ok(Map.of("message", "Review Delete Success"));
    }

    @PostMapping("/{review_id}/like")
    public ResponseEntity<Map<String,String>> likeReview(@PathVariable("review_id") Long reviewId, @RequestBody LikeRequest likeRequest, @CookieValue("accessToken") String accessToken) {
        log.info("Like review for {}", reviewId);
        User user = authService.getUser(accessToken);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message","Login Required"));
        }

        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message","Review not existing"));
        }

        if (review.getUser().getId() != user.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message","Not allowed to delete this review"));
        }

        Integer likes = review.getLikes();

        if (likeRequest.isLike()) {
            review.setLikes(likes+1);
        }

        if (!likeRequest.isLike()) {
            review.setLikes(likes-1);
        }

        reviewRepository.save(review);

        return ResponseEntity.ok(Map.of("message", "Review Update Success"));
    }
}
