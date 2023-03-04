package com.java.guesthouse.mypage.ui;

import javax.servlet.http.HttpSession;

import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.review.service.ReviewService;

@Controller
@RequestMapping("/my-page")
public class MyPageController {

    private final ReviewService reviewService;

    public MyPageController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(value = "/reviews", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getMyReviewsPage(HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberCode");

        Pair<Integer, Integer> myReviewsCount = reviewService.getCountsOfMyReviews(memberId);

        ModelAndView mav = new ModelAndView("guestdelluna/myReviewList.tiles");
        mav.addObject("countHouseReview", myReviewsCount.getFirst());
        mav.addObject("countExpReview", myReviewsCount.getSecond());

        return mav;
    }
    
}
