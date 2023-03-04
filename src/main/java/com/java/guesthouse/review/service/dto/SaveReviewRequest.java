package com.java.guesthouse.review.service.dto;

public record SaveReviewRequest(
        Long guestHouseId,
        Integer rate,
        String content
) {
}
