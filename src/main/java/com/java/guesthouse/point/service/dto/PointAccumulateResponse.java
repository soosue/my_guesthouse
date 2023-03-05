package com.java.guesthouse.point.service.dto;

import com.java.guesthouse.point.domain.PointAccumulate;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "포인트적립내역")
public record PointAccumulateResponse(
        @Schema(description = "포인트적립내역 id")
        Long id,
        @Schema(description = "포인트 적립된 게스트하우스명")
        String guestHouseName,
        @Schema(description = "적립날짜")
        String createdAt,
        @Schema(description = "적립포인트")
        Long point
) {
    public static PointAccumulateResponse from(PointAccumulate pointAccumulate) {
        return new PointAccumulateResponse(
                pointAccumulate.getId(),
                pointAccumulate.getGuestHouseName(),
                pointAccumulate.getCreatedDate().toString(),
                pointAccumulate.getPoint()
        );
    }
}
