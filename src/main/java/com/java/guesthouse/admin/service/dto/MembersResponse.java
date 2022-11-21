package com.java.guesthouse.admin.service.dto;

import java.util.List;

import com.java.guesthouse.common.dto.PageInfo;

public record MembersResponse(
    List<MemberResponse> data,
    PageInfo pageInfo
) {
    public static MembersResponse of(List<MemberResponse> members, Integer totalPages) {
        return new MembersResponse(members, new PageInfo(totalPages));
    }
}
