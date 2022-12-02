package com.java.guesthouse.guestdelluna.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping(value = "guestdelluna/reviewUpdateOk.do", method = RequestMethod.GET)
    public void reviewUpdateOk(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        dellunaService.reviewUpdateOk(mav);

    }

    //체험후기삭제
    @RequestMapping(value = "guestdelluna/reviewDelete.do", method = RequestMethod.GET)
    public ModelAndView reviewDelete(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        dellunaService.reviewDelete(mav);

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
}
