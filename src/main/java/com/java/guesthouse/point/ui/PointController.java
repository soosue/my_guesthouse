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
import com.java.guesthouse.point.service.dto.ListResponse;
import com.java.guesthouse.point.service.dto.PointAccumulateResponse;
import com.java.guesthouse.point.service.dto.PointResponse;
import com.java.guesthouse.point.service.dto.PointUseResponse;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

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
    @Parameter(name = "page", in = ParameterIn.QUERY, description = "페이지 번호 0..n",
            content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
    @Parameter(name = "size", in = ParameterIn.QUERY, description = "페이지 크기",
            content = @Content(schema = @Schema(type = "integer", defaultValue = "5")))
    public ResponseEntity<ListResponse<PointAccumulateResponse>> getMyPointAccumulates(
            HttpSession session,
            @Parameter(hidden = true) @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Long memberId = getMemberId(session);
        return ResponseEntity.ok(pointService.getPointAccumulates(memberId, pageable));
    }

    @GetMapping("/uses/me")
    public ResponseEntity<ListResponse<PointUseResponse>> getMyPointUses(
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
