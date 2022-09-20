package com.java.myguesthouse.member.service;

public interface MemberService {
    Long saveMember(MemberSaveApplication memberSaveApplication);

    void checkEmail();

    void login();

    void kakaoLogin();
}
