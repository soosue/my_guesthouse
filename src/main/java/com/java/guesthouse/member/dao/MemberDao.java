package com.java.guesthouse.member.dao;

import com.java.guesthouse.member.dto.MemberDto;

public interface MemberDao {

	public int register(MemberDto memberDto);

	public int emailCheck(String email);

	public String login(String email, String password);

	public MemberDto memberSel(String email, String password);

	public int inserKakao(String email, String memberImgPath, String memberName);


	public int kakaoEmailChk(String email);

	public int getMemberCode(String email);

}
