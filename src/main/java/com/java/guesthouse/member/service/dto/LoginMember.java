package com.java.guesthouse.member.service.dto;

import java.util.Optional;

import com.java.guesthouse.member.domain.Member;

public record LoginMember(
        Long memberCode,
        String email,
        String memberLevel,
        String accessToken
) {
    public static Optional<LoginMember> optionalOf(Member member, String accessToken) {
        if (member == null) {
            return Optional.empty();
        }
        return Optional.of(new LoginMember(member.getId(), member.getEmail(), member.getMemberLevel(), accessToken));
    }

    public static Optional<LoginMember> optionalOf(Member member) {
        return optionalOf(member, null);
    }
}
