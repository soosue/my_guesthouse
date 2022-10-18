package com.java.guesthouse.host.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.java.guesthouse.experience.service.dto.ExperienceDto;
import com.java.guesthouse.file.dto.FileDto;
import com.java.guesthouse.guestreserve.dto.GuestReserveDto;
import com.java.guesthouse.host.service.dto.ExReservationListDto;
import com.java.guesthouse.host.service.dto.HostDto;
import com.java.guesthouse.host.service.dto.ReservationListDto;
import com.java.guesthouse.host.service.dto.SearchDateList;
import com.java.guesthouse.host.service.dto.SearchDateListCount;
import com.java.guesthouse.member.service.dto.MemberDto;

@Repository
public class HostDao {
    private final SqlSessionTemplate sqlSession;

    public HostDao(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int subImgUpload(FileDto fileDto) {
        return sqlSession.insert("host.dao.mapper.subImgUpload", fileDto);
    }

    public int hostRegister(HostDto hostDto) {
        return sqlSession.insert("host.dao.mapper.hostRegister", hostDto);
    }

    public int memberProfileImg(MemberDto memberDto) {
        return sqlSession.update("host.dao.mapper.memberProfileUpdate", memberDto);
    }

    public int memberCode(String email) {
        return sqlSession.selectOne("host.dao.mapper.memberCode", email);
    }

    public int mainImgUpload(FileDto fileDto) {
        return sqlSession.insert("host.dao.mapper.mainImgUpload", fileDto);
    }

    public int houseCode() {
        return sqlSession.selectOne("host.dao.mapper.houseCode");
    }

    public List<HostDto> houseList(int memberCode, int startRow, int endRow) {
        Map<String, Integer> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        return sqlSession.selectList("host.dao.mapper.houseList", map);
    }

    public int hostCancel(int houseCode) {
        return sqlSession.update("host.dao.mapper.hostCancel", houseCode);
    }

    public int exCancel(int exCode) {
        return sqlSession.update("host.dao.mapper.exCancel", exCode);
    }

    public List<String> houseNameList(int memberCode) {
        return sqlSession.selectList("host.dao.mapper.houseNameList", memberCode);
    }

    public List<String> exNameList(int memberCode) {
        return sqlSession.selectList("host.dao.mapper.exNameList", memberCode);
    }

    public int getHouseCode(String houseName) {
        return sqlSession.selectOne("host.dao.mapper.getHouseCode", houseName);
    }

    public int getExCode(String exName) {
        return sqlSession.selectOne("host.dao.mapper.getExCode", exName);
    }

    public List<ReservationListDto> reserveViewList(int houseCode, int startRow, int endRow) {
        Map<String, Integer> map = new HashMap<>();
        map.put("houseCode", houseCode);
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        return sqlSession.selectList("host.dao.mapper.reserveViewList", map);
    }

    public List<ExReservationListDto> exReserveViewList(int exCode, int startRow, int endRow) {
        Map<String, Integer> map = new HashMap<>();
        map.put("exCode", exCode);
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        return sqlSession.selectList("host.dao.mapper.exReserveViewList", map);
    }


    public int getHouseCount(String email) {
        return sqlSession.selectOne("host.dao.mapper.getHouseCount", email);
    }

    public int getReserveCount(int houseCode) {
        return sqlSession.selectOne("host.dao.mapper.getReserveCount", houseCode);
    }

    public int getExReserveCount(int exCode) {
        return sqlSession.selectOne("host.dao.mapper.getExReserveCount", exCode);
    }

    public int getExCount(int memberCode) {
        return sqlSession.selectOne("host.dao.mapper.getExCount", memberCode);
    }

    public List<ExperienceDto> experienceList(int memberCode, int startRow, int endRow) {
        Map<String, Integer> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        return sqlSession.selectList("host.dao.mapper.experienceList", map);
    }

    public List<GuestReserveDto> getSales(int memberCode) {
        return sqlSession.selectList("host.dao.mapper.getSales", memberCode);
    }

    public SearchDateListCount getSearchDateCount(int memberCode, String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        return sqlSession.selectOne("host.dao.mapper.getSearchDateCount", map);
    }

    public List<SearchDateList> searchDateList(int memberCode, String startDate, String endDate, int startRow,
                                               int endRow) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        return sqlSession.selectList("host.dao.mapper.searchDateList", map);
    }

    public MemberDto selectMemberDto(int memberCode) {
        return sqlSession.selectOne("host.dao.mapper.selectMemberDto", memberCode);
    }

    public List<HostDto> ahouseList(int memberCode) {
        return sqlSession.selectList("host.dao.mapper.ahouseList", memberCode);
    }

    public String getLocal(String localName) {
        return sqlSession.selectOne("host.dao.mapper.getLocal", localName);
    }

    public int houseNameCheck(String houseName) {
        int check = 0;
        String checkHouseName = sqlSession.selectOne("host.dao.mapper.houseNameCheck", houseName);
        if (checkHouseName != null) check = 1;

        return check;
    }


}
