package com.java.guesthouse.review.ui;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.guestdelluna.service.dto.HouseReviewDto;
import com.java.guesthouse.review.service.ReviewService;
import com.java.guesthouse.review.service.dto.UpdateReviewRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@RestController
@RequestMapping("/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("")
    public ModelAndView saveReview(HttpSession session, HouseReviewDto reviewDto) {
        long guestHouseId = reviewDto.getHouseCode();
        long memberId = (long) session.getAttribute("memberCode");

        Long reviewId = reviewService.saveReview(reviewDto.getRevContent(), reviewDto.getRevRate(), memberId, guestHouseId);

        ModelAndView mav = new ModelAndView("guestHousePage/reviewOk.tiles");
        mav.addObject("guestHouseId", guestHouseId);
        mav.addObject("check", reviewId);

        return mav;
    }

    @PutMapping("/{id}")
    @Operation(summary = "리뷰 수정", description = "리뷰 id를 이용하여 리뷰를 수정합니다.")
    public ResponseEntity<Void> updateReview(
            HttpSession session,
            @Parameter(name = "id", description = "리뷰의 id", in = ParameterIn.PATH) @PathVariable("id") Long reviewId,
            @RequestBody UpdateReviewRequest updateReviewRequest
    ) {
        Long memberId = (Long) session.getAttribute("memberCode");
        reviewService.updateReview(reviewId, memberId, updateReviewRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "리뷰 삭제", description = "리뷰 id를 이용하여 리뷰를 삭제합니다.")
    public ResponseEntity<Void> deleteReview(
            HttpSession session,
            @Parameter(name = "id", description = "리뷰의 id", in = ParameterIn.PATH) @PathVariable("id") Long reviewId
    ) {
        Long memberId = (Long) session.getAttribute("memberCode");
        reviewService.deleteReview(reviewId, memberId);
        return ResponseEntity.ok().build();
    }

}
