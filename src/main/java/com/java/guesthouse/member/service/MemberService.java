package com.java.guesthouse.member.service;

import org.springframework.web.servlet.ModelAndView;

public interface MemberService {

    void memberRegisterOk(ModelAndView mav);

    void memberEmailCheck(ModelAndView mav);

    void memberLoginOk(ModelAndView mav);

    void kakaoLogin(ModelAndView mav);


}
