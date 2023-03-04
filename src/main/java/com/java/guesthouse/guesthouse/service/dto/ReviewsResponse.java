package com.java.guesthouse.guesthouse.service.dto;

import java.util.List;

public record ReviewsResponse(
        List<ReviewResponse> data
) {
    public static ReviewsResponse from(List<ReviewResponse> reviews) {
        return new ReviewsResponse(reviews);
    }
}
