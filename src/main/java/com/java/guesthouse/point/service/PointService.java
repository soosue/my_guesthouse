package com.java.guesthouse.point.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.guesthouse.point.domain.PointAccumulate;
import com.java.guesthouse.point.domain.PointAccumulateRepository;
import com.java.guesthouse.point.service.dto.PointAccumulateResponse;
import com.java.guesthouse.point.service.dto.PointAccumulatesResponse;

@Service
@Transactional(readOnly = true)
public class PointService {
    private final PointAccumulateRepository pointAccumulateRepository;

    public PointService(PointAccumulateRepository pointAccumulateRepository) {
        this.pointAccumulateRepository = pointAccumulateRepository;
    }

    public PointAccumulatesResponse getPointAccumulates(Long memberId, Pageable pageable) {
        Page<PointAccumulate> pointAccumulates = pointAccumulateRepository.findByMemberId(memberId, pageable);
        return PointAccumulatesResponse.of(
                pointAccumulates.stream()
                        .map(PointAccumulateResponse::from)
                        .toList(),
                pointAccumulates.getTotalPages()
        );
    }
}
