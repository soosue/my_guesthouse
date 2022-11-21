package com.java.guesthouse.point.service.dto;

import com.java.guesthouse.point.domain.PointAccumulate;

public record PointAccumulateResponse(
        Long id,
        String guestHouseName,
        String createdAt,
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
