package com.java.guesthouse.review.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.java.guesthouse.guesthouse.domain.GuestHouseDao;
import com.java.guesthouse.guesthouse.service.dto.ReviewResponse;
import com.java.guesthouse.guesthouse.service.dto.ReviewsResponse;
import com.java.guesthouse.guestreserve.dto.GuestReserveDto;
import com.java.guesthouse.guestreserve.dto.ReviewDto;
import com.java.guesthouse.review.domain.Review;
import com.java.guesthouse.review.domain.ReviewRepository;

@Service
public class ReviewService {

    private final GuestHouseDao guestHouseDao;
    private final ReviewRepository reviewRepository;

    public ReviewService(GuestHouseDao guestHouseDao, ReviewRepository reviewRepository) {
        this.guestHouseDao = guestHouseDao;
        this.reviewRepository = reviewRepository;
    }

    public Long saveReview(String content, int rate, Long memberId, Long guestHouseId) {
        List<GuestReserveDto> reservations = guestHouseDao.findReservationByGuestHouseIdAndMemberId(guestHouseId, memberId);
        Optional<GuestReserveDto> reservationOptional = reservations.stream()
                .filter(this::isNotReviewed)
                .findAny();

        if (reservationOptional.isEmpty()) {
            return -1L; // 더 이상 작성할 후기가 없음. 이미 모든 후기 작성 완료
        }

        GuestReserveDto reservation = reservationOptional.get();

        Review review = new Review((long) reservation.getReserveCode(), memberId, content, rate);
        reviewRepository.save(review);

        return review.getId();
    }

    private boolean isNotReviewed(GuestReserveDto reservation) {
        return guestHouseDao.reviewChk(reservation.getReserveCode()) == 0;
    }

    public ReviewsResponse getReviewsOfGuestHouse(Pageable pageable, Long guestHouseId) {
        Slice<ReviewDto> reviews = reviewRepository.findByGuestHouseId(guestHouseId, pageable);

        return ReviewsResponse.from(
                reviews.getContent().stream()
                        .map(ReviewResponse::from)
                        .toList()
        );
    }
}
