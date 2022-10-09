package com.java.guesthouse.member.domain;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImp implements MemberDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public MemberDaoImp(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public int insertKakao(String email, String memberImgPath, String memberName) {
        Map<String, Object> hMap = new HashMap<>();

        hMap.put("email", email);
        hMap.put("memberImgPath", memberImgPath);
        hMap.put("memberName", memberName);

        return sqlSessionTemplate.insert("dao.MemberMapper.insertKakao", hMap);
    }

    @Override
    public int kakaoEmailCheck(String email) {
        return sqlSessionTemplate.selectOne("dao.MemberMapper.kakaoEmailCheck", email);
    }

    @Override
    public int findIdByEmail(String email) {
        return sqlSessionTemplate.selectOne("dao.MemberMapper.findIdByEmail", email);
    }

}
