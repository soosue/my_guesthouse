package com.java.guesthouse.member.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.aop.HomeAspect;
import com.java.guesthouse.member.service.MemberService;
import com.java.guesthouse.member.service.dto.MemberSaveRequest;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/member/register.do", method = RequestMethod.GET)
    public ModelAndView memberRegister(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Member Register");

        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        mav.setViewName("member/register.tiles");
        return mav;
    }

    @PostMapping("/v1/member")
    public ModelAndView saveMember(HttpServletRequest request, HttpServletResponse response, MemberSaveRequest memberSaveRequest) {

        Long id = memberService.saveMember(memberSaveRequest);

        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);
        mav.addObject("response", response);
        mav.addObject("memberDto", memberSaveRequest);
        mav.addObject("check", id);
        mav.setViewName("member/registerOk.tiles");

        return mav;
    }

    @GetMapping(value = "/v1/member/check")
    public void memberIdCheck(HttpServletRequest request, HttpServletResponse response) {
        HomeAspect.logger.info(HomeAspect.logMsg + "Email Check");

        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);
        mav.addObject("response", response);
        memberService.memberEmailCheck(mav);
    }

    @RequestMapping(value = "/member/login.do", method = RequestMethod.GET)
    public ModelAndView memberLogin(HttpServletRequest request, HttpServletResponse response) {
        HomeAspect.logger.info(HomeAspect.logMsg + "member Login");
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        mav.setViewName("member/login.tiles");


        return mav;
    }

    @RequestMapping(value = "/member/loginOk.do", method = RequestMethod.POST)
    public ModelAndView memberLoginOk(HttpServletRequest request, HttpServletResponse response) {
        HomeAspect.logger.info(HomeAspect.logMsg + "member LoginOk");

        String beforeURL = request.getHeader("REFERER");
        //HomeAspect.logger.info(HomeAspect.logMsg+request.getRequestURL());
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);
        mav.addObject("beforeURL", beforeURL);
        memberService.memberLoginOk(mav);

        mav.setViewName("member/loginOk.tiles");


        return mav;
    }

    @RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
    public ModelAndView memberLogout(HttpServletRequest request, HttpServletResponse response) {
        HomeAspect.logger.info(HomeAspect.logMsg + "member Logout");
        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        mav.setViewName("member/logout.tiles");

        return mav;
    }


    @RequestMapping(value = "/member/kakaoLogin.do", method = RequestMethod.GET)
    public ModelAndView KakaoLogin(HttpServletRequest request, HttpServletResponse response) {
        HomeAspect.logger.info(HomeAspect.logMsg + "kakao login");

        String beforeURL = request.getHeader("REFERER");

        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);
        mav.addObject("beforeURL", beforeURL);

        memberService.kakaoLogin(mav);
        mav.setViewName("member/loginOk.tiles");
        return mav;

    }


}
