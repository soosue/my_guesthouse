package com.java.guesthouse.guesthouse.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public interface GuestHouseService {
    void guestHouseRead(ModelAndView mav);

    void guestHouseReserv(ModelAndView mav);

    void reservComplete(ModelAndView mav);

    void kakaoPaySuccess(ModelAndView mav);

    void kakaoPayCompleteOk(ModelAndView mav);

    void limitCheck(ModelAndView mav);

    String review(ModelAndView mav);

    void reviewOk(ModelAndView mav);

    void reviewUpdate(ModelAndView mav);

    void reviewUpdateOk(ModelAndView mav);

    void reviewDelete(ModelAndView mav);

    Map<String, Object> review(HttpServletRequest request);
}
