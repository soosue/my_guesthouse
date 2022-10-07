package com.java.guesthouse.member.service;

import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.member.service.dto.MemberSaveRequest;

public interface MemberService {

    Long saveMember(MemberSaveRequest memberSaveRequest);

    void memberEmailCheck(ModelAndView mav);

    void memberLoginOk(ModelAndView mav);

    void kakaoLogin(ModelAndView mav);
}
