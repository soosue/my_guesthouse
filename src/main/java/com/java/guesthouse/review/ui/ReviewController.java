package com.java.guesthouse.review.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.aop.HomeAspect;
import com.java.guesthouse.guestdelluna.service.dto.HouseReviewDto;
import com.java.guesthouse.guesthouse.service.GuestHouseService;

@RestController
@RequestMapping("/v1/reviews")
public class ReviewController {
    private final GuestHouseService guestHouseService;

    public ReviewController(GuestHouseService guestHouseService) {
        this.guestHouseService = guestHouseService;
    }

    // 게스트하우스 후기 작성 완료
    @PostMapping("")
    public ModelAndView saveReview(HttpServletRequest request, HttpServletResponse response, HttpSession session, HouseReviewDto reviewDto) {
        System.out.println("review write, list Ok");

        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);
        mav.addObject("response", response);
        mav.addObject("reviewDto", reviewDto);

        guestHouseService.reviewOk(mav, (long) session.getAttribute("memberCode"));

        HomeAspect.logger.info(HomeAspect.logMsg + reviewDto.toString());

        return mav;
    }

}
