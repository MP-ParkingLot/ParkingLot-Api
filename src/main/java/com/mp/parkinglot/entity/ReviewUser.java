package com.mp.parkinglot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review_user")
public class ReviewUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Review review;

    @ManyToOne
    private User user;

    private Boolean isLiked;

    public ReviewUser(Review review, User user, Boolean isLiked) {
        this.review = review;
        this.user = user;
        this.isLiked = isLiked;
    }
}
