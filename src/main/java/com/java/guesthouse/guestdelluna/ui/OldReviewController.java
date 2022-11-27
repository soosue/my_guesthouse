package com.java.guesthouse.guestdelluna.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.guestdelluna.service.DellunaService;

@Controller
public class OldReviewController {

    private final DellunaService dellunaService;

    public OldReviewController(DellunaService dellunaService) {
        this.dellunaService = dellunaService;
    }

    @RequestMapping(value = "guestdelluna/reviewUpdate.do", method = RequestMethod.GET)
    public ModelAndView reviewUpdate(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);
        dellunaService.reviewUpdate(mav);

        return mav;
    }

    @RequestMapping(value = "guestdelluna/reviewUpdateOk.do", method = RequestMethod.GET)
    public void reviewUpdateOk(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        dellunaService.reviewUpdateOk(mav);

    }

    @RequestMapping(value = "guestdelluna/houseReviewUpdateOk.do", method = RequestMethod.GET)
    public void houseReviewUpdateOk(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        dellunaService.houseReviewUpdateOk(mav);

    }

    //체험후기삭제
    @RequestMapping(value = "guestdelluna/reviewDelete.do", method = RequestMethod.GET)
    public ModelAndView reviewDelete(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        dellunaService.reviewDelete(mav);

        return mav;

    }

    //하우스후기삭제
    @RequestMapping(value = "guestdelluna/houseReviewDelete.do", method = RequestMethod.GET)
    public ModelAndView houseReviewDelete(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        dellunaService.houseReviewDelete(mav);

        return mav;

    }

    @RequestMapping(value = "guestdelluna/revExpAjax.do", method = RequestMethod.GET)
    public ModelAndView revExpAjax(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);
        mav.addObject("response", response);

        dellunaService.revExpAjax(mav);

        return mav;

    }

    @RequestMapping(value = "guestdelluna/revHouseAjax.do", method = RequestMethod.GET)
    public ModelAndView revHouseAjax(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);
        mav.addObject("response", response);

        dellunaService.revHouseAjax(mav);

        return mav;

    }

    //내가 쓴 리뷰 목록 조회
    @RequestMapping(value = "/mypage/reviews.page", method = RequestMethod.GET)
    public ModelAndView getMyReviewsPage(HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberCode");

        Pair<Integer, Integer> myReviewsCount = dellunaService.getMyReviewsCount(memberId);

        ModelAndView mav = new ModelAndView("guestdelluna/myReviewList.tiles");
        mav.addObject("countHouseReview", myReviewsCount.getFirst());
        mav.addObject("countExpReview", myReviewsCount.getSecond());

        return mav;
    }
}
