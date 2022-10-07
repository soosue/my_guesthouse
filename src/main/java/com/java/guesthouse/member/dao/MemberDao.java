package com.java.guesthouse.member.dao;

import com.java.guesthouse.member.service.dto.MemberDto;

public interface MemberDao {

    int emailCheck(String email);

    String login(String email, String password);

    MemberDto memberSel(String email, String password);

    int inserKakao(String email, String memberImgPath, String memberName);

    int kakaoEmailChk(String email);

    int getMemberCode(String email);

}
