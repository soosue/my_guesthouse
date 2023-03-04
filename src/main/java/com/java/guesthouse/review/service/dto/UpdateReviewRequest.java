package com.java.guesthouse.review.service.dto;

public record UpdateReviewRequest(
        Integer rate,
        String content
) {
}
