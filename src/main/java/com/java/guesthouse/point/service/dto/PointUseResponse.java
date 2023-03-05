package com.java.guesthouse.point.service.dto;

import com.java.guesthouse.point.domain.PointUse;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "포인트사용내역")
public record PointUseResponse(
        @Schema(description = "포인트사용내역 id")
        Long id,
        @Schema(description = "포인트 사용한 게스트하우스명")
        String placeName,
        @Schema(description = "사용날짜")
        String createdAt,
        @Schema(description = "사용포인트")
        Long point
) {
    public static PointUseResponse from(PointUse pointUse) {
        return new PointUseResponse(
                pointUse.getId(),
                pointUse.getPlaceName(),
                pointUse.getCreatedDate().toString(),
                pointUse.getPoint()
        );
    }
}
