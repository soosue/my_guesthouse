package com.java.guesthouse.point.service.dto;

import java.util.List;

import com.java.guesthouse.common.dto.PageInfo;

public record PointAccumulatesResponse(
        List<PointAccumulateResponse> pointAccumulates,
        PageInfo pageInfo
) {
    public static PointAccumulatesResponse of(List<PointAccumulateResponse> pointAccumulateList, int totalPages) {
        return new PointAccumulatesResponse(pointAccumulateList, new PageInfo(totalPages));
    }
}
