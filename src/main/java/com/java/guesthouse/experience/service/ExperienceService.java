package com.java.guesthouse.experience.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public interface ExperienceService {

    void exHostOk(ModelAndView mav);

    void hostAddress(ModelAndView mav);

    String exReview(ModelAndView mav);

    void exReviewOk(ModelAndView mav);

    void exReviewUpdate(ModelAndView mav);

    void exReviewUpdateOk(ModelAndView mav);

    void exReviewDelete(ModelAndView mav);

    void exPage(ModelAndView mav);

    void exReserve(ModelAndView mav);

    void exReserveOk(ModelAndView mav);

    // 달력
    void exReserveCal(ModelAndView mav);

    // 잭슨
    Map<String, Object> exReview(HttpServletRequest request);

    // 카카오 페이
    void kakaoPaySuccess(ModelAndView mav);

    void kakaoPaySuccessOk(ModelAndView mav);

    ArrayList<String> exDisableDates(String exCode, String people);


}
