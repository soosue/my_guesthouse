package com.java.guesthouse.guesthouse.service.dto;

import java.util.Date;

import com.java.guesthouse.guestreserve.dto.ReviewDto;

public record ReviewResponse(
        int reserveCode,
        int memberCode,
        Date revDate,
        String revContent,
        int revRate,
        String email
) {
    public static ReviewResponse from(ReviewDto reviewDto) {
        return new ReviewResponse(
                reviewDto.getReserveCode(),
                reviewDto.getMemberCode(),
                reviewDto.getRevDate(),
                reviewDto.getRevContent(),
                reviewDto.getRevRate(),
                reviewDto.getEmail()
        );
    }
}
