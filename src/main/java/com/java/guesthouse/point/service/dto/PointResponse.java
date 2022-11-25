package com.java.guesthouse.point.service.dto;

public record PointResponse(
        Long point
) {
    public static PointResponse from(Long point) {
        return new PointResponse(point);
    }
}
