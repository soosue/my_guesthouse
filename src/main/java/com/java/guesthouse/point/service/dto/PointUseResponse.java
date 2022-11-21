package com.java.guesthouse.point.service.dto;

import com.java.guesthouse.guesthouse.domain.PointUse;

public record PointUseResponse(
        Long id,
        String placeName,
        String createdAt,
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
