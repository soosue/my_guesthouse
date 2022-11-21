package com.java.guesthouse.point.ui;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.guestdelluna.service.DellunaService;
import com.java.guesthouse.point.service.PointService;

@Controller
public class PointController2 {
    private final PointService pointService;
    private final DellunaService dellunaService;

    public PointController2(PointService pointService, DellunaService dellunaService) {
        this.pointService = pointService;
        this.dellunaService = dellunaService;
    }


    @RequestMapping(value = "/v1/pointaccumulates/me", method = RequestMethod.GET)
    public ModelAndView managePointAjax(HttpServletRequest request, @PageableDefault Pageable pageable) {

//        Long memberId = (Long)session.getAttribute("memberCode");

//        List<PointAccumulateDto> pointAccumulates = dellunaService.getPointAccumulates(1, memberId);
//        List<PointUse> pointUses = dellunaService.getPointUses(1, memberId);
//        mav.addObject("accuPoint", pointAccumulates);
//        mav.addObject("usePoint", pointUses);
        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        dellunaService.pointManageAjax(mav);

        return mav;
    }
}
