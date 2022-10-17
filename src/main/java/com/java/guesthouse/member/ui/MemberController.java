package com.java.guesthouse.member.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.api.oauth.KakaoOAuth;
import com.java.guesthouse.member.service.MemberService;
import com.java.guesthouse.member.service.dto.KakaoLoginRequest;
import com.java.guesthouse.member.service.dto.LoginRequest;
import com.java.guesthouse.member.service.dto.MemberSaveRequest;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final KakaoOAuth kakaoOAuth;

    public MemberController(MemberService memberService, KakaoOAuth kakaoOAuth) {
        this.memberService = memberService;
        this.kakaoOAuth = kakaoOAuth;
    }

    @GetMapping("/v1/members/register.page")
    public ModelAndView registerMemberPage(HttpServletRequest request) {

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

    @ResponseBody
    @GetMapping(value = "/v1/members/check")
    public String checkEmail(@RequestParam String email) {

        int check = memberService.checkEmail(email);

        return check + "";
    }

    @PostMapping(value = "/v1/members/login")
    public ModelAndView login(HttpServletRequest request, LoginRequest loginRequest) {

        memberService.login(loginRequest, request);

        ModelAndView mav = new ModelAndView("member/loginOk.tiles");
        mav.addObject("beforeURL", request.getHeader("REFERER"));

        return mav;
    }

    @GetMapping(value = "/v1/members/logout")
    public ModelAndView logout(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        String accessToken = (String)session.getAttribute("accessToken");
        if (accessToken != null) {
            kakaoOAuth.logout(accessToken);
        }

        session.invalidate();

        return new ModelAndView("member/logout.tiles");
    }

    @GetMapping(value = "/v1/members/kakaologin")
    public ModelAndView kakaoLogin(HttpServletRequest request, KakaoLoginRequest kakaoLoginRequest) {

        memberService.kakaoLogin(kakaoLoginRequest, request);

        ModelAndView mav = new ModelAndView("member/loginOk.tiles");
        mav.addObject("beforeURL", request.getHeader("REFERER"));

        return mav;
    }
}
