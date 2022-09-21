package com.java.myguesthouse.member;

import com.java.myguesthouse.member.domain.Member;
import com.java.myguesthouse.member.service.MemberSaveApplication;

public class MemberFixture {
    public static final Member 일반회원코롱 = Member.memberFrom(
            "코롱", "korong@email.com", "kororong", "010-1234-5678");

    public static final MemberSaveApplication 일반회원코롱신청서 = new MemberSaveApplication(
            "코롱", "korong@email.com", "kororong", "010-1234-5678");

    public static final Member 사장코롱이 = Member.hostFrom(
            "코롱이", "korong2@email.com", "kororong", "010-1234-5678");

}
