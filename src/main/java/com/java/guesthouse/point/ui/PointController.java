package com.java.guesthouse.point.ui;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.guesthouse.point.service.PointService;
import com.java.guesthouse.point.service.dto.PointAccumulatesResponse;
import com.java.guesthouse.point.service.dto.PointResponse;
import com.java.guesthouse.point.service.dto.PointUsesResponse;

@RestController
@RequestMapping("/v1/points")
public class PointController {
    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping("/me")
    public ResponseEntity<PointResponse> getMyPoint(HttpSession session) {
        Long memberId = getMemberId(session);
        return ResponseEntity.ok(pointService.getPointByMemberId(memberId));
    }

    @GetMapping("/accumulates/me")
    public ResponseEntity<PointAccumulatesResponse> getMyPointAccumulates(
            HttpSession session,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Long memberId = getMemberId(session);
        return ResponseEntity.ok(pointService.getPointAccumulates(memberId, pageable));
    }

    @GetMapping("/uses/me")
    public ResponseEntity<PointUsesResponse> getMyPointUses(
            HttpSession session,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Long memberId = getMemberId(session);
        return ResponseEntity.ok(pointService.getPointUses(memberId, pageable));
    }

    private Long getMemberId(HttpSession session) {
        return (Long) session.getAttribute("memberCode");
    }
}
