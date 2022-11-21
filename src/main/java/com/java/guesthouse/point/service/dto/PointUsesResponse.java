package com.java.guesthouse.point.service.dto;

import java.util.List;

import com.java.guesthouse.common.dto.PageInfo;

public record PointUsesResponse(
        List<PointUseResponse> data,
        PageInfo pageInfo
) {
    public static PointUsesResponse of(List<PointUseResponse> pointUseList, int totalPages) {
        return new PointUsesResponse(pointUseList, new PageInfo(totalPages));
    }
}
