package com.java.guesthouse.member.ui;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.member.service.LoginService;
import com.java.guesthouse.member.service.MemberService;
import com.java.guesthouse.member.service.dto.KakaoLoginRequest;
import com.java.guesthouse.member.service.dto.LoginMember;
import com.java.guesthouse.member.service.dto.LoginRequest;
import com.java.guesthouse.member.service.dto.MemberSaveRequest;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final LoginService loginService;

    public MemberController(MemberService memberService, LoginService loginService) {
        this.memberService = memberService;
        this.loginService = loginService;
    }

    @GetMapping("/v1/members/register.page")
    public ModelAndView registerMemberPage(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("request", request);

        mav.setViewName("member/register.tiles");
        return mav;
    }

    @PostMapping("/v1/members")
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

    @ResponseBody
    @GetMapping(value = "/v1/members/check")
    public String checkEmail(@RequestParam String email) {

        int check = memberService.checkEmail(email);

        return check + "";
    }

    @PostMapping(value = "/v1/members/login")
    public ModelAndView login(HttpServletRequest request, LoginRequest loginRequest) {

        Optional<LoginMember> loginMemberOptional = loginService.login(loginRequest);

        HttpSession session = request.getSession();
        loginMemberOptional.ifPresent(loginMember -> setSession(session, loginMember));

        ModelAndView mav = new ModelAndView("member/loginOk.tiles");
        mav.addObject("beforeURL", request.getHeader("REFERER"));

        return mav;
    }

    @GetMapping(value = "/v1/members/kakaologin")
    public ModelAndView kakaoLogin(HttpServletRequest request, KakaoLoginRequest kakaoLoginRequest) {

        Optional<LoginMember> loginMemberOptional = loginService.kakaoLogin(kakaoLoginRequest);

        HttpSession session = request.getSession();
        loginMemberOptional.ifPresent(loginMember -> setSession(session, loginMember));

        ModelAndView mav = new ModelAndView("member/loginOk.tiles");
        mav.addObject("beforeURL", request.getHeader("REFERER"));

        return mav;
    }

    private void setSession(HttpSession session, LoginMember member) {
        session.setAttribute("memberLevel", member.memberLevel());
        session.setAttribute("email", member.email());
        session.setAttribute("memberCode", member.memberCode());
        session.setAttribute("accessToken", member.accessToken());
    }

    @GetMapping(value = "/v1/members/logout")
    public ModelAndView logout(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String accessToken = (String) session.getAttribute("accessToken");

        loginService.logout(accessToken);

        session.invalidate();

        return new ModelAndView("member/logout.tiles");
    }
}
