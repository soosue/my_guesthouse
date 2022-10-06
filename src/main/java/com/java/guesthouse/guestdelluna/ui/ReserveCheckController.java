package com.java.guesthouse.guestdelluna.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.guestdelluna.service.DellunaService;

@Controller
public class ReserveCheckController {

    private final DellunaService dellunaService;

    public ReserveCheckController(DellunaService dellunaService) {
        this.dellunaService = dellunaService;
    }

    @RequestMapping(value = "guestdelluna/checkReserve.do", method = RequestMethod.GET)
    public ModelAndView checkReservation(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        dellunaService.reserveCheck(mav);

        return mav;

    }

    @RequestMapping(value = "guestdelluna/viewMyReserveExp.do", method = RequestMethod.GET)
    public ModelAndView viewMyExp(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        dellunaService.myReserveExp(mav);

        return mav;

    }

    @RequestMapping(value = "guestdelluna/cancelReserve.do", method = RequestMethod.GET)
    public void cancleReservation(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);
        mav.addObject("response", response);

        try {
            dellunaService.reserveCancle(mav);
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "guestdelluna/cancelExp.do", method = RequestMethod.GET)
    public void cancleExpRes(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);
        mav.addObject("response", response);

        try {
            dellunaService.expCancle(mav);
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
