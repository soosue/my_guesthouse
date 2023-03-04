package com.java.guesthouse.review.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "리뷰")
public record UpdateReviewRequest(
        @Schema(description = "별점", defaultValue = "5", allowableValues = {"1", "2", "3", "4", "5"})
        Integer rate,
        @Schema(description = "리뷰내용", defaultValue = "")
        String content
) {
}
