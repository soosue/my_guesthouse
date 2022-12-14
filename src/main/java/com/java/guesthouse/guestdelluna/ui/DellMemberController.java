package com.java.guesthouse.guestdelluna.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.guestdelluna.service.DellunaService;
import com.java.guesthouse.guestdelluna.service.dto.MemberDto;

@Controller
public class DellMemberController {

    private final DellunaService dellunaService;

    public DellMemberController(DellunaService dellunaService) {
        this.dellunaService = dellunaService;
    }

    //회원수정
    @RequestMapping(value = "guestdelluna/memberUpdate.do", method = RequestMethod.GET)
    public ModelAndView memberUpdate(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        dellunaService.updateMember(mav);

        return mav;

    }

    //수정ok
    @RequestMapping(value = "guestdelluna/memberUpdateOk.do", method = RequestMethod.POST)
    public ModelAndView memberUpdateOk(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);
        mav.addObject("memberDto", memberDto);

        dellunaService.updateMemberOk(mav);

        return mav;

    }

    @RequestMapping(value = "guestdelluna/memberDelete.do", method = RequestMethod.GET)
    public ModelAndView memberDelete(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        dellunaService.deleteMember(mav);

        return mav;

    }

    @RequestMapping(value = "guestdelluna/memberDeleteOk.do", method = RequestMethod.GET)
    public ModelAndView memberDeleteOk(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("request", request);

        dellunaService.deleteMemberOk(mav);

        return mav;


    }

}
