package com.java.guesthouse.point.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "포인트")
public record PointResponse(
        @Schema(description = "보유 포인트")
        Long point
) {
    public static PointResponse from(Long point) {
        return new PointResponse(point);
    }
}
