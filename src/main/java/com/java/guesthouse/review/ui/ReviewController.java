package com.java.guesthouse.review.ui;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.guestdelluna.service.dto.HouseReviewDto;
import com.java.guesthouse.review.service.ReviewService;

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

}
