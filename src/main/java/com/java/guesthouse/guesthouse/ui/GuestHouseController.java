package com.java.guesthouse.guesthouse.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.guesthouse.service.GuestHouseService;
import com.java.guesthouse.guesthouse.service.dto.ReviewsResponse;
import com.java.guesthouse.review.service.ReviewService;

@Controller
public class GuestHouseController {

    private final GuestHouseService guestHouseService;
    private final ReviewService reviewService;

    public GuestHouseController(GuestHouseService guestHouseService, ReviewService reviewService) {
        this.guestHouseService = guestHouseService;
        this.reviewService = reviewService;
    }

    @RequestMapping(value = "/guesthouses/details.page", method = RequestMethod.GET)
    public ModelAndView guestHousePageRead(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        guestHouseService.guestHouseRead(mav);

        return mav;
    }

    // 게스트 하우스의 리뷰 목록 조회
    @ResponseBody
    @RequestMapping(value = "/v1/guesthouses/{id}/reviews", method = RequestMethod.GET)
    public ResponseEntity<ReviewsResponse> getReviewsOfGuestHouse(
            @PathVariable(value = "id") Long guestHouseId,
            @PageableDefault(size = 1, sort = "revdate", direction = Sort.Direction.DESC) Pageable pageable
    ) {

        return ResponseEntity.ok(reviewService.getReviewsOfGuestHouse(pageable, guestHouseId));
    }

    @RequestMapping(value = "/guestHousePage/limitCheck.do", method = RequestMethod.GET)
    public ModelAndView limitCheck(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        guestHouseService.limitCheck(mav);

        return mav;
    }

    @RequestMapping(value = "guestHousePage/reservation.do", method = RequestMethod.GET)
    public ModelAndView guestHouseReserv(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        guestHouseService.guestHouseReserv(mav);

        return mav;
    }


    @RequestMapping(value = "/guestHousePage/reserveComplete.do", method = RequestMethod.GET)
    public ModelAndView reservComplete(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        guestHouseService.reservComplete(mav);

        return mav;
    }


    @RequestMapping(value = "/guestHousePage/reserveCompleteOk.do", method = RequestMethod.GET)
    public ModelAndView reservCompleteCheckOk(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        guestHouseService.kakaoPayCompleteOk(mav);

        return mav;
    }


    @RequestMapping(value = "/guestHousePage/kakaoPay.do", method = RequestMethod.GET)
    public ModelAndView kakaoPaySuccess(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        guestHouseService.kakaoPaySuccess(mav);

        return mav;
    }
}
