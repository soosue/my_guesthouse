package com.java.guesthouse.point.ui;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.guesthouse.point.service.PointService;
import com.java.guesthouse.point.service.dto.PointAccumulatesResponse;
import com.java.guesthouse.point.service.dto.PointResponse;
import com.java.guesthouse.point.service.dto.PointUsesResponse;

@Controller
public class PointController2 {
    private final PointService pointService;

    public PointController2(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping("/v1/points/me")
    public ResponseEntity<PointResponse> getMyPoint(HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberCode");
        return ResponseEntity.ok(pointService.getPointByMemberId(memberId));
    }

    @GetMapping("/v1/pointaccumulates/me")
    public ResponseEntity<PointAccumulatesResponse> getMyPointAccumulates(
            HttpSession session,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Long memberId = (Long) session.getAttribute("memberCode");
        return ResponseEntity.ok(pointService.getPointAccumulates(memberId, pageable));
    }

    @GetMapping("/v1/pointuses/me")
    public ResponseEntity<PointUsesResponse> getMyPointUses(
            HttpSession session,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Long memberId = (Long) session.getAttribute("memberCode");
        return ResponseEntity.ok(pointService.getPointUses(memberId, pageable));
    }
}
