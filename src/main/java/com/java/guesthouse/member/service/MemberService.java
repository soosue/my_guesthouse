package com.java.guesthouse.member.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.java.guesthouse.member.service.dto.LoginRequest;
import com.java.guesthouse.member.service.dto.MemberSaveRequest;

public interface MemberService {

    Long saveMember(MemberSaveRequest memberSaveRequest);

    int checkEmail(String email);

    void login(LoginRequest loginRequest, HttpServletRequest request);

    void kakaoLogin(ModelAndView mav);
}
