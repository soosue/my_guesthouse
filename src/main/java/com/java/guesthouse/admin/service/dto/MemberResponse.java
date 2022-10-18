package com.java.guesthouse.admin.service.dto;

import com.java.guesthouse.member.domain.Member;

public record MemberResponse(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String createdAt,
        Long point,
        String memberLevel
) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getId(), member.getName(), member.getEmail(),
                member.getPhoneNumber(), member.getCreatedAt().toString(),
                member.getPoint(), member.getMemberLevel());
    }
}
