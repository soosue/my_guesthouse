package com.java.guesthouse.guesthouse.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.java.guesthouse.file.dto.FileDto;
import com.java.guesthouse.guestdelluna.service.dto.HouseReviewDto;
import com.java.guesthouse.guestdelluna.service.dto.MsgDto;
import com.java.guesthouse.guestreserve.dto.GuestReserveDto;
import com.java.guesthouse.guestreserve.dto.RemainDto;
import com.java.guesthouse.host.service.dto.HostDto;
import com.java.guesthouse.member.service.dto.MemberDto;

@Repository
public class GuestHouseDao {
    private final SqlSessionTemplate sqlSessionTemplate;

    public GuestHouseDao(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<FileDto> guestHouseImg(int houseCode) {
        Map<String, Integer> hMap = new HashMap<>();
        hMap.put("houseCode", houseCode);
        return sqlSessionTemplate.selectList("dao.GuestHouseMapper.guestHouseImgList", hMap);
    }

    public int getMemberCode(String email) {
        return sqlSessionTemplate.selectOne("dao.GuestHouseMapper.getMemberCode", email);
    }

    public HostDto getHostInfo(int houseCode) {
        // TODO Auto-generated method stub
        return sqlSessionTemplate.selectOne("dao.GuestHouseMapper.getHostInfo", houseCode);
    }

    public int getPoint(String email) {
        // TODO Auto-generated method stub
        return sqlSessionTemplate.selectOne("dao.GuestHouseMapper.getMemberPoint", email);
    }

    public MemberDto getHostList(int hostCode) {
        // TODO Auto-generated method stub
        return sqlSessionTemplate.selectOne("dao.GuestHouseMapper.getHost", hostCode);
    }

    public String getHouseName(Long houseCode) {
        // TODO Auto-generated method stub
        return sqlSessionTemplate.selectOne("dao.GuestHouseMapper.getHouseName", houseCode);
    }

    public int insertReserveInfo(GuestReserveDto guestReserveDto) {
        // TODO Auto-generated method stub
        return sqlSessionTemplate.insert("dao.GuestHouseMapper.insertReserveInfo", guestReserveDto);
    }

    public int getReserveCode(int houseCode, int memberCode, Date checkIn) {
        // TODO Auto-generated method stub

        Map<String, Object> hMap = new HashMap<>();
        hMap.put("houseCode", houseCode);
        hMap.put("memberCode", memberCode);
        hMap.put("checkIn", checkIn);

        return sqlSessionTemplate.selectOne("dao.GuestHouseMapper.getReserveCode", hMap);
    }

    public int updatePoint(int memberPoint, int memberCode) {
        // TODO Auto-generated method stub
        Map<String, Object> hMap = new HashMap<>();
        hMap.put("memberPoint", memberPoint);
        hMap.put("memberCode", memberCode);

        return sqlSessionTemplate.update("dao.GuestHouseMapper.updatePoint", hMap);
    }

    public int insertRemain(Date checkIn, int people, int houseCode) {
        // TODO Auto-generated method stub
        Map<String, Object> hMap = new HashMap<>();
        hMap.put("resDate", checkIn);
        hMap.put("people", people);
        hMap.put("houseCode", houseCode);

        return sqlSessionTemplate.insert("dao.GuestHouseMapper.insertRemain", hMap);
    }

    public List<RemainDto> getRemain(int houseCode) {
        // TODO Auto-generated method stub
        return sqlSessionTemplate.selectList("dao.GuestHouseMapper.getRemain", houseCode);
    }

    public MemberDto getMemberInfo(String email) {
        // TODO Auto-generated method stub
        return sqlSessionTemplate.selectOne("dao.GuestHouseMapper.getMemberInfo", email);
    }

    public int insertMsg(MsgDto msgDto) {
        // TODO Auto-generated method stub
        return sqlSessionTemplate.insert("dao.GuestHouseMapper.insertMsg", msgDto);
    }

    public List<GuestReserveDto> findReservationByGuestHouseIdAndMemberId(long houseCode, long memberCode) {
        Map<String, Object> hMap = new HashMap<>();
        hMap.put("memberCode", memberCode);
        hMap.put("houseCode", houseCode);

        return sqlSessionTemplate.selectList("dao.GuestHouseMapper.findReservationByGuestHouseIdAndMemberId", hMap);
    }

    public int reviewChk(int reserveCode) {

        return sqlSessionTemplate.selectOne("dao.GuestHouseMapper.reviewChk", reserveCode);
    }

    public int reviewDelete(int reserveCode) {
        return sqlSessionTemplate.delete("dao.GuestHouseMapper.reviewDelete", reserveCode);
    }
}
