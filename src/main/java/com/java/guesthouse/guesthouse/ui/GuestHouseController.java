package com.java.guesthouse.guesthouse.ui;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.guestdelluna.service.dto.HouseReviewDto;
import com.java.guesthouse.guesthouse.service.GuestHouseService;

@Controller
public class GuestHouseController {

    private final GuestHouseService guestHouseService;

    public GuestHouseController(GuestHouseService guestHouseService) {
        this.guestHouseService = guestHouseService;
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
    public Map<String, Object> getReviewsOfGuestHouse(HttpServletRequest request, @PathVariable(value = "id") Long guestHouseId) {
        return guestHouseService.getReviewsOfGuestHouse(request, guestHouseId);
    }

    // 후기 수정하기 눌렀을 때
    @RequestMapping(value = "/guestHousePage/reviewUpdate.do", method = RequestMethod.GET)
    public ModelAndView reviewUpdate(HttpServletRequest request, HttpServletResponse response,
                                     HouseReviewDto reviewDto) {
        System.out.println("exReview 수정하기");
        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        mav.addObject("reviewDto", reviewDto);

        guestHouseService.reviewUpdate(mav);

        return mav;
    }

    // 수정 완료 눌렀을 때
    @RequestMapping(value = "/guestHousePage/reviewUpdateOk.do", method = RequestMethod.GET)
    public void exReviewUpdateOk(HttpServletRequest request, HttpServletResponse response,
                                 HouseReviewDto reviewDto) {
        System.out.println("exReview 수정완료");

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);
        mav.addObject("response", response);
        mav.addObject("reviewDto", reviewDto);

        guestHouseService.reviewUpdateOk(mav);

    }

    // 삭제 눌렀을 때
    @RequestMapping(value = "/guestHousePage/reviewDelete.do", method = RequestMethod.GET)
    public ModelAndView exReviewDelete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("exReview 삭제하기");
        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);
        guestHouseService.reviewDelete(mav);

        return mav;
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
