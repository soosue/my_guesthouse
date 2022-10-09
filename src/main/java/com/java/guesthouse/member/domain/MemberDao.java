package com.java.guesthouse.member.domain;

public interface MemberDao {

    int insertKakao(String email, String memberImgPath, String memberName);

    int kakaoEmailCheck(String email);

    int findIdByEmail(String email);

}
