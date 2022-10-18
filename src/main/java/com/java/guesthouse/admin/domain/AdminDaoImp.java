package com.java.guesthouse.admin.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.java.guesthouse.experience.service.dto.ExperienceDto;
import com.java.guesthouse.member.service.dto.MemberDto;

@Repository
public class AdminDaoImp implements AdminDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public AdminDaoImp(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    // 게스트 하우스 관리
    @Override
    public int houseCount() {
        return sqlSessionTemplate.selectOne("dao.AdminMapper.houseCount");
    }

    @Override
    public List<MemberDto> houseList(int startRow, int endRow) {
        Map<String, Integer> hMap = new HashMap<>();
        hMap.put("startRow", startRow);
        hMap.put("endRow", endRow);

        return sqlSessionTemplate.selectList("dao.AdminMapper.houseList", hMap);
    }

    // 체험 관리
    @Override
    public int experienceCount() {
        return sqlSessionTemplate.selectOne("dao.AdminMapper.experienceCount");
    }

    @Override
    public List<ExperienceDto> experienceList(int startRow, int endRow) {
        Map<String, Integer> hMap = new HashMap<>();
        hMap.put("startRow", startRow);
        hMap.put("endRow", endRow);

        return sqlSessionTemplate.selectList("dao.AdminMapper.experienceList", hMap);
    }

    @Override
    public int experienceStateOk(int exCode) {
        return sqlSessionTemplate.update("dao.AdminMapper.experienceStateOk", exCode);
    }

    @Override
    public int experienceStateNo(int exCode) {
        return sqlSessionTemplate.update("dao.AdminMapper.experienceStateNo", exCode);
    }

    @Override
    public int guestHouseStateOk(int houseCode) {
        // TODO Auto-generated method stub
        return sqlSessionTemplate.update("dao.AdminMapper.guestHouseStateOk", houseCode);
    }

    @Override
    public int guestHouseStateNo(int houseCode) {
        // TODO Auto-generated method stub
        return sqlSessionTemplate.update("dao.AdminMapper.guestHouseStateNo", houseCode);

    }
}
