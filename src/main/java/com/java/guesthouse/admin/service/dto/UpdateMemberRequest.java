package com.java.guesthouse.admin.service.dto;

public record UpdateMemberRequest(
        Long point,
        String memberLevel
) {
}
