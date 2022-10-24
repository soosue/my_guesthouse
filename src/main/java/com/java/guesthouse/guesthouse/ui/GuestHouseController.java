package com.java.guesthouse.guesthouse.ui;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.aop.HomeAspect;
import com.java.guesthouse.guestdelluna.service.dto.HouseReviewDto;
import com.java.guesthouse.guesthouse.service.GuestHouseService;

@Controller
public class GuestHouseController {

    private final GuestHouseService guestHouseService;

    public GuestHouseController(GuestHouseService guestHouseService) {
        this.guestHouseService = guestHouseService;
    }

    // 게스트 하우스 정보 조회(게스트 하우스 상세페이지)
    @RequestMapping(value = "/guestHousePage/guestHouse.do", method = RequestMethod.GET)
    public ModelAndView guestHousePageRead(HttpServletRequest request, HttpServletResponse response, HouseReviewDto reviewDto) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);
        mav.addObject("reviewDto", reviewDto);

        guestHouseService.guestHouseRead(mav);

        return mav;
    }

    // 게스트 하우스 상세페이지에서 후기 더보기 눌렀을 때 슬라이스
    @ResponseBody
    @RequestMapping(value = "/guestHousePage/review.do", method = RequestMethod.GET)
    public Map<String, Object> guestHousePageReview(HttpServletRequest request, HttpServletResponse response, HouseReviewDto reviewDto) {

        System.out.println("r@@@@@@@");

        return guestHouseService.review(request);
    }

    // 게스트 하우스 상세페이지에서 후기 작성 버튼 눌렀을 때
    @RequestMapping(value = "/guestHousePage/reviewOk.do", method = RequestMethod.GET)
    public ModelAndView guestHousePageReviewOk(HttpServletRequest request, HttpServletResponse response, HouseReviewDto reviewDto) {
        System.out.println("review write, list Ok");

        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);
        mav.addObject("response", response);
        mav.addObject("reviewDto", reviewDto);

        guestHouseService.reviewOk(mav);

        HomeAspect.logger.info(HomeAspect.logMsg + reviewDto.toString());

        return mav;
    }

    // 안 사용함
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

    // 후기 수정 버튼 눌렀을 때
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

    // 안 사용함
    @RequestMapping(value = "/guestHousePage/reviewDelete.do", method = RequestMethod.GET)
    public ModelAndView exReviewDelete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("exReview 삭제하기");
        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);
        guestHouseService.reviewDelete(mav);

        return mav;
    }

    // 예악하기 버튼 누르면 validation 확인
    @RequestMapping(value = "/guestHousePage/limitCheck.do", method = RequestMethod.GET)
    public ModelAndView limitCheck(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        guestHouseService.limitCheck(mav);

        return mav;
    }

    // 예약하기 상세 페이지로 이동
    @RequestMapping(value = "guestHousePage/reservation.do", method = RequestMethod.GET)
    public ModelAndView guestHouseReserv(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        guestHouseService.guestHouseReserv(mav);

        return mav;
    }

    // 예약 상세페이지에서 무통장입금으로 예약 요청하기 버튼 눌러서 예약 완료 처리
    @RequestMapping(value = "/guestHousePage/reserveComplete.do", method = RequestMethod.GET)
    public ModelAndView reservComplete(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        guestHouseService.reservComplete(mav);

        return mav;
    }

    // 예약 상세페이지에서 카드결제로 예약 요청하기 버튼 누를 때
    @RequestMapping(value = "/guestHousePage/kakaoPay.do", method = RequestMethod.GET)
    public ModelAndView kakaoPaySuccess(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        guestHouseService.kakaoPaySuccess(mav);

        return mav;

    }

    // 카드결제(카카오페이) 완료 후 예약 완료 처리
    @RequestMapping(value = "/guestHousePage/reserveCompleteOk.do", method = RequestMethod.GET)
    public ModelAndView reservCompleteCheckOk(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        guestHouseService.kakaoPayCompleteOk(mav);

        return mav;
    }
}
