package com.java.guesthouse.point.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.guesthouse.member.domain.Member;
import com.java.guesthouse.member.service.MemberService;
import com.java.guesthouse.point.domain.PointAccumulate;
import com.java.guesthouse.point.domain.PointAccumulateRepository;
import com.java.guesthouse.point.domain.PointUse;
import com.java.guesthouse.point.domain.PointUseRepository;
import com.java.guesthouse.point.service.dto.ListResponse;
import com.java.guesthouse.point.service.dto.PointAccumulateResponse;
import com.java.guesthouse.point.service.dto.PointResponse;
import com.java.guesthouse.point.service.dto.PointUseResponse;

@Service
@Transactional(readOnly = true)
public class PointService {
    private final PointAccumulateRepository pointAccumulateRepository;
    private final PointUseRepository pointUseRepository;
    private final MemberService memberService;

    public PointService(PointAccumulateRepository pointAccumulateRepository, PointUseRepository pointUseRepository, MemberService memberService) {
        this.pointAccumulateRepository = pointAccumulateRepository;
        this.pointUseRepository = pointUseRepository;
        this.memberService = memberService;
    }

    public ListResponse<PointAccumulateResponse> getPointAccumulates(Long memberId, Pageable pageable) {
        Page<PointAccumulate> pointAccumulates = pointAccumulateRepository.findByMemberId(memberId, pageable);
        return ListResponse.of(
                pointAccumulates.stream()
                        .map(PointAccumulateResponse::from)
                        .toList(),
                pointAccumulates.getTotalPages()
        );
    }

    public ListResponse<PointUseResponse> getPointUses(Long memberId, Pageable pageable) {
        Page<PointUse> pointUses = pointUseRepository.findByMemberId(memberId, pageable);
        return ListResponse.of(
                pointUses.stream()
                        .map(PointUseResponse::from)
                        .toList(),
                pointUses.getTotalPages()
        );
    }

    public PointResponse getPointByMemberId(Long memberId) {
        Member member = memberService.findById(memberId);

        return PointResponse.from(member.getPoint());
    }
}
