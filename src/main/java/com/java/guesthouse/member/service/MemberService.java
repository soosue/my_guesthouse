package com.java.guesthouse.member.service;

import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.member.service.dto.MemberSaveRequest;

public interface MemberService {

    Long saveMember(MemberSaveRequest memberSaveRequest);

    int checkEmail(String email);

    void memberLoginOk(ModelAndView mav);

    void kakaoLogin(ModelAndView mav);
}
