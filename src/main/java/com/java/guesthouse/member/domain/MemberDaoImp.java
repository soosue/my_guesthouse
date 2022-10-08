package com.java.guesthouse.member.domain;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.java.guesthouse.member.service.dto.MemberDto;

@Repository
public class MemberDaoImp implements MemberDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public MemberDaoImp(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public int register(MemberDto memberDto) {

        return sqlSessionTemplate.insert("dao.MemberMapper.insert", memberDto);
    }

    @Override
    public int emailCheck(String email) {

        int check = 0;
        String CheckEmail = sqlSessionTemplate.selectOne("dao.MemberMapper.emailCheck", email);
        if (CheckEmail != null) check = 1;

        return check;
    }

    @Override
    public String login(String email, String password) {

        Map<String, Object> hMap = new HashMap<>();

        hMap.put("email", email);
        hMap.put("password", password);

        return sqlSessionTemplate.selectOne("dao.MemberMapper.login", hMap);
    }


    @Override
    public MemberDto memberSel(String email, String password) {

        Map<String, Object> hMap = new HashMap<>();

        hMap.put("email", email);
        hMap.put("password", password);

        return sqlSessionTemplate.selectOne("dao.MemberMapper.memberSel", hMap);
    }

    @Override
    public int inserKakao(String email, String memberImgPath, String memberName) {
        Map<String, Object> hMap = new HashMap<>();

        hMap.put("email", email);
        hMap.put("memberImgPath", memberImgPath);
        hMap.put("memberName", memberName);

        return sqlSessionTemplate.insert("dao.MemberMapper.insertKakao", hMap);
    }

    @Override
    public int kakaoEmailChk(String email) {
        return sqlSessionTemplate.selectOne("dao.MemberMapper.kakaoEmailChk", email);
    }

    @Override
    public int getMemberCode(String email) {
        return sqlSessionTemplate.selectOne("dao.MemberMapper.getMemberCode", email);
    }

}
