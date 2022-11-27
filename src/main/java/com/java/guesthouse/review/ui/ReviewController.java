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

@RestController
@RequestMapping("/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 게스트하우스 리뷰 작성 완료
    @PostMapping("")
    public ModelAndView saveReview(HttpSession session, HouseReviewDto reviewDto) {
        long guestHouseId = reviewDto.getHouseCode();
        ;
        long memberId = (long) session.getAttribute("memberCode");

        Long reviewId = reviewService.saveReview(reviewDto.getRevContent(), reviewDto.getRevRate(), memberId, guestHouseId);

        ModelAndView mav = new ModelAndView("guestHousePage/reviewOk.tiles");
        mav.addObject("guestHouseId", guestHouseId);
        mav.addObject("check", reviewId);

        return mav;
    }

    // 게스트하우스 상세 페이지에서 리뷰 수정 요청
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReview(
            HttpSession session,
            @PathVariable("id") Long reviewId,
            @RequestBody UpdateReviewRequest updateReviewRequest
    ) {
        Long memberId = (Long) session.getAttribute("memberCode");
        reviewService.updateReview(reviewId, memberId, updateReviewRequest);
        return ResponseEntity.ok().build();
    }

    // 게스트하우스 상세 페이지에서 리뷰 삭제 요청
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(HttpSession session, @PathVariable("id") Long reviewId) {
        Long memberId = (Long) session.getAttribute("memberCode");
        reviewService.deleteReview(reviewId, memberId);
        return ResponseEntity.ok().build();
    }

}
