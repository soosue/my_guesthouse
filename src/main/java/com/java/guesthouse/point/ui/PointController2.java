package com.java.guesthouse.point.ui;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.guesthouse.point.service.PointService;
import com.java.guesthouse.point.service.dto.PointAccumulatesResponse;

@Controller
public class PointController2 {
    private final PointService pointService;

    public PointController2(PointService pointService) {
        this.pointService = pointService;
    }

    @RequestMapping(value = "/v1/pointaccumulates/me", method = RequestMethod.GET)
    public ResponseEntity<PointAccumulatesResponse> getPointAccumulates(
            HttpSession session,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Long memberId = (Long) session.getAttribute("memberCode");
        return ResponseEntity.ok(pointService.getPointAccumulates(memberId, pageable));
    }
}
