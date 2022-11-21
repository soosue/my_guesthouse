package com.java.guesthouse.guestdelluna.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.guestdelluna.service.DellunaService;

@Controller
public class PointController {

    private final DellunaService dellunaService;

    public PointController(DellunaService dellunaService) {
        this.dellunaService = dellunaService;
    }

    @GetMapping("/manage/points")
    public ModelAndView managePoint() {
        return new ModelAndView("guestdelluna/myPoint.tiles");
    }

    @RequestMapping(value = "guestdelluna/managePointUseAjax.do", method = RequestMethod.GET)
    public ModelAndView managePointUseAjax(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);
        mav.addObject("response", response);

        dellunaService.pointManageUseAjax(mav);

        return mav;

    }

    @RequestMapping(value = "guestdelluna/payList.do", method = RequestMethod.GET)
    public ModelAndView payList(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        dellunaService.listPay(mav);

        return mav;

    }

    @RequestMapping(value = "guestdelluna/payExpAjax.do", method = RequestMethod.GET)
    public ModelAndView payExpAjax(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);
        mav.addObject("response", response);

        dellunaService.payExpAjax(mav);

        return mav;

    }

    @RequestMapping(value = "guestdelluna/payHouseAjax.do", method = RequestMethod.GET)
    public ModelAndView payHouseAjax(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);
        mav.addObject("response", response);

        dellunaService.payHouseAjax(mav);

        return mav;

    }

    @RequestMapping(value = "guestdelluna/deleteExpPayList.do", method = RequestMethod.GET)
    public void deletePayListExp(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        dellunaService.deleteExpPayList(mav);

    }

    @RequestMapping(value = "guestdelluna/deleteExpPayHouse.do", method = RequestMethod.GET)
    public void deletePayListHouse(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        dellunaService.deleteExpPayHouse(mav);

    }

}
