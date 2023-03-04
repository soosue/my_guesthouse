package com.java.guesthouse.point.service.dto;

import java.util.List;

import com.java.guesthouse.common.dto.PageInfo;

public record ListResponse<T>(
        List<T> data,
        PageInfo pageInfo
) {
    public static <T> ListResponse<T> of(List<T> pointAccumulateList, int totalPages) {
        return new ListResponse<>(pointAccumulateList, new PageInfo(totalPages));
    }
}
