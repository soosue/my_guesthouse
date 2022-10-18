package com.java.guesthouse.guestdelluna.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.java.guesthouse.aop.HomeAspect;
import com.java.guesthouse.guestdelluna.service.dto.ExpReservationDto;
import com.java.guesthouse.guestdelluna.service.dto.ExpReviewDto;
import com.java.guesthouse.guestdelluna.service.dto.ExpZzimDto;
import com.java.guesthouse.guestdelluna.service.dto.HouseDto;
import com.java.guesthouse.guestdelluna.service.dto.HouseReservationDto;
import com.java.guesthouse.guestdelluna.service.dto.HouseReviewDto;
import com.java.guesthouse.guestdelluna.service.dto.HouseZzimDto;
import com.java.guesthouse.guestdelluna.service.dto.MemberDto;
import com.java.guesthouse.guestdelluna.service.dto.MsgDto;
import com.java.guesthouse.guestdelluna.service.dto.MyExReviewList;
import com.java.guesthouse.guestdelluna.service.dto.MyHouseReviewList;
import com.java.guesthouse.guestdelluna.service.dto.NewExpResDto;
import com.java.guesthouse.guestdelluna.service.dto.NewExpReserveDto;
import com.java.guesthouse.guestdelluna.service.dto.NewExpReviewDto;
import com.java.guesthouse.guestdelluna.service.dto.NewExpZzimDto;
import com.java.guesthouse.guestdelluna.service.dto.NewHouseResDto;
import com.java.guesthouse.guestdelluna.service.dto.NewHouseReserveDto;
import com.java.guesthouse.guestdelluna.service.dto.NewHouseReviewDto;
import com.java.guesthouse.guestdelluna.service.dto.NewHouseZzimDto;
import com.java.guesthouse.guestdelluna.service.dto.PointAccumulate;
import com.java.guesthouse.guestdelluna.service.dto.PointUse;
import com.java.guesthouse.host.service.dto.ExReviewListDto;
import com.java.guesthouse.host.service.dto.HostExListDto;
import com.java.guesthouse.host.service.dto.HostHouseListDto;
import com.java.guesthouse.host.service.dto.HouseReviewListDto;

@Repository
public class DellunaDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public DellunaDao(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public HouseReviewDto selectReview(int memberCode) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.selectOneReview", memberCode);
    }

    public int reviewCount() {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.reviewCount");
    }

    public List<HouseReviewDto> reviewList(int startRow, int endRow) {


        Map<String, Object> map = new HashMap<>();
        map.put("startRow", startRow);
        map.put("endRow", endRow);

        return sqlSessionTemplate.selectList("dao.dellunaMapper.reviewList", map);
    }

    public int countExp(int memberCode) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.countExp", memberCode);
    }

    public List<ExpReservationDto> findExpList(int memberCode) {


        return sqlSessionTemplate.selectList("dao.dellunaMapper.findExpList", memberCode);
    }

    public int countHouse(int memberCode) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.countHouse", memberCode);
    }

    public List<HouseReservationDto> findHouseList(int memberCode) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        return sqlSessionTemplate.selectList("dao.dellunaMapper.findHouseList", map);
    }

    public MemberDto selectForUpdate(String email) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.selectForUpdate", email);
    }

    public int updateMember(MemberDto memberDto) {

        int check = 0;

        if (memberDto.getMemberImgName() == null) {
            HomeAspect.logger.info(HomeAspect.logMsg + 11111);
            check = sqlSessionTemplate.update("dao.dellunaMapper.updateMember", memberDto);
        } else {
            check = sqlSessionTemplate.update("dao.dellunaMapper.updateMemberFile", memberDto);
        }
        return check;
    }

    public int memberDeleteOk(String email, String password) {

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);

        return sqlSessionTemplate.delete("dao.dellunaMapper.memberDeleteOk", map);
    }

    public HouseReservationDto myResHouse(int reserveCode) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.myResHouse", reserveCode);
    }

    public ExpReservationDto myResExp(int exCode) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.myResExp", exCode);
    }

    public int doZzim(Map<String, Object> dataMap, String zzim) {
        if (zzim != null)
            return sqlSessionTemplate.insert("dao.dellunaMapper.doZzim", dataMap);
        else
            return sqlSessionTemplate.delete("dao.dellunaMapper.cancelZzim", dataMap);

    }

    public int selectMemberCode(String email) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.selectMemberCode", email);
    }

    public int selectExCode(int memberCode) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.selectExCode", memberCode);
    }

    public int selectReserveCode(int memberCode) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.selectReserveCode", memberCode);
    }

    public int deleteExpReserve(int exCode) {

        return sqlSessionTemplate.delete("dao.dellunaMapper.deleteExpReserve", exCode);
    }

    public int deleteHouseReserve(int reserveCode) {

        return sqlSessionTemplate.delete("dao.dellunaMapper.deleteHouseReserve", reserveCode);
    }

    public List<HouseDto> houseInfo(int memberCode) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.houseInfo", memberCode);
    }

    public List<Integer> allHouse(int memberCode) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.allHouse", memberCode);
    }

    public List<Integer> houseCodeThis(int houseCodeFromRes) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.houseCodeThis", houseCodeFromRes);
    }

    public List<HouseDto> houseInfo(int memberCode, int houseCode) {


        /*
         * List<Object> list = sqlSessionTemplate.selectList("houseInfo2",memberCode);
         *
         * System.out.println("list: "+list.toString());
         *
         *
         *
         * Map<String,Object> map = (Map<String, Object>)
         * sqlSessionTemplate.selectList("houseInfo",memberCode);
         *
         * System.out.println(map.toString());
         */

        return /* sqlSessionTemplate.selectList("houseInfo", memberCode) */ null;

    }

    public List<String> findHouseName(int memberCode) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.findHouseName", memberCode);
    }

    public List<Integer> ExpResNum(int memberCode) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.ExpResNum", memberCode);
    }

    public List<Integer> expRealNum(int expResNum) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.expRealNum", expResNum);
    }

    public List<String> myExName(int memberCode) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.myExName", memberCode);
    }

    public List<Integer> findMyResCode(int memberCode) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.findMyResCode", memberCode);
    }

    public int updateState(int houseReserveCode) {

        return sqlSessionTemplate.update("dao.dellunaMapper.updateState", houseReserveCode);
    }

    public List<Integer> findMyExpCode(int memberCode) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.findMyExpCode", memberCode);
    }

    public int updateExpState(int expReserveCode) {

        return sqlSessionTemplate.update("dao.dellunaMapper.updateExpState", expReserveCode);
    }

    public List<ExpZzimDto> dtoExpZzim(int memberCode) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.dtoExpZzim", memberCode);

    }

    public List<String> zzimExName(int memberCode) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.zzimExName", memberCode);
    }

    public int deleteExpZzim(int exCode, int memberCode) {

        Map<String, Object> map = new HashMap<>();

        map.put("memberCode", memberCode);
        map.put("exCode", exCode);

        return sqlSessionTemplate.delete("dao.dellunaMapper.deleteExpZzim", map);
    }

    public List<HouseZzimDto> dtoHouseZzim(int memberCode) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.dtoHouseZzim", memberCode);
    }

    public List<String> zzimHouseName(int memberCode) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.zzimHouseName", memberCode);
    }

    public int deleteHouseZzim(int houseCode, int memberCode) {

        Map<String, Object> map = new HashMap<>();

        map.put("memberCode", memberCode);
        map.put("houseCode", houseCode);

        return sqlSessionTemplate.delete("dao.dellunaMapper.deleteHouseZzim", map);
    }

    public int deletePayListExp(int exReserveCode) {

        return sqlSessionTemplate.delete("dao.dellunaMapper.deletePayListExp", exReserveCode);
    }

    public int deletePayListHouse(int houseReserveCode) {

        return sqlSessionTemplate.delete("dao.dellunaMapper.deletePayListHouse", houseReserveCode);
    }

    public List<HouseReservationDto> findHouseListWithString(int memberCode, String state) {


        Map<String, Object> map = new HashMap<>();

        map.put("memberCode", memberCode);
        map.put("state", state);


        return sqlSessionTemplate.selectList("dao.dellunaMapper.findHouseListWithString", map);
    }

    public List<String> findHouseNameWithString(int memberCode, String state) {

        Map<String, Object> map = new HashMap<>();

        map.put("memberCode", memberCode);
        map.put("state", state);

        return sqlSessionTemplate.selectList("dao.dellunaMapper.findHouseNameWithString", map);
    }

    public List<ExpReservationDto> findExpListWithString(int memberCode, String state) {

        Map<String, Object> map = new HashMap<>();

        map.put("memberCode", memberCode);
        map.put("state", state);

        return sqlSessionTemplate.selectList("dao.dellunaMapper.findExpListWithString", map);
    }

    public List<String> myExNameWithString(int memberCode, String state) {

        Map<String, Object> map = new HashMap<>();

        map.put("memberCode", memberCode);
        map.put("state", state);

        return sqlSessionTemplate.selectList("dao.dellunaMapper.myExNameWithString", map);
    }

    public int getCountAccu(int memberCode) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.getCountAccu", memberCode);
    }

    public List<HostHouseListDto> getHouseList(int memberCode) {
        return sqlSessionTemplate.selectList("host.dao.mapper.getHouseList", memberCode);
    }

    public List<HouseReviewListDto> getHouseReviewList(int memberCode) {
        return sqlSessionTemplate.selectList("host.dao.mapper.getHouseReviewList", memberCode);
    }

    public List<ExReviewListDto> getExReviewList(int memberCode) {
        return sqlSessionTemplate.selectList("host.dao.mapper.getExReviewList", memberCode);
    }

    public List<HostExListDto> getExList(int memberCode) {
        return sqlSessionTemplate.selectList("host.dao.mapper.getExList", memberCode);
    }

    public List<HouseReviewListDto> getHouseReviewListScroll(int memberCode, int startRow, int endRow) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        return sqlSessionTemplate.selectList("host.dao.mapper.getHouseReviewListScroll", map);
    }


    public int getCountUse(int memberCode) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.getCountUse", memberCode);
    }

    public List<PointAccumulate> myAccuPoint(int memberCode, int startRow, int endRow) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("startRow", startRow);
        map.put("endRow", endRow);

        return sqlSessionTemplate.selectList("dao.dellunaMapper.myAccuPoint", map);
    }

    public List<PointUse> myUsePoint(int memberCode, int startRow, int endRow) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("startRow", startRow);
        map.put("endRow", endRow);

        return sqlSessionTemplate.selectList("dao.dellunaMapper.myUsePoint", map);
    }

    public int expReviewCount(int memberCode) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.expReviewCount", memberCode);
    }

    public int houseReviewCount(int memberCode) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.houseReviewCount", memberCode);
    }

    public List<ExpReviewDto> myExpReview(int memberCode, int startRow, int endRow) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("startRow", startRow);
        map.put("endRow", endRow);

        return sqlSessionTemplate.selectList("dao.dellunaMapper.myExpReview", map);
    }

    public List<HouseReviewDto> myHouseReview(int memberCode, int houseStartRow, int houseEndRow) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("houseStartRow", houseStartRow);
        map.put("houseEndRow", houseEndRow);

        return sqlSessionTemplate.selectList("dao.dellunaMapper.myHouseReview", map);
    }

    public ExpReviewDto findMyReview(int memberCode, int exReserveCode) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("exReserveCode", exReserveCode);

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.findMyReview", map);
    }

    public int deleteReview(int exReserveCode, int memberCode) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("exReserveCode", exReserveCode);

        return sqlSessionTemplate.delete("dao.dellunaMapper.deleteReview", map);
    }

    public int deleteHouseReview(int reserveCode, int memberCode) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("reserveCode", reserveCode);
        return sqlSessionTemplate.delete("dao.dellunaMapper.deleteHouseReview", map);
    }

    public int updateExpReview(int memberCode, int exReserveCode, String revContent) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("exReserveCode", exReserveCode);
        map.put("revContent", revContent);

        return sqlSessionTemplate.update("dao.dellunaMapper.updateExpReview", map);
    }

    public int selectMSG(int memberCode, String msgCheck) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("msgCheck", msgCheck);
        return sqlSessionTemplate.selectOne("dao.dellunaMapper.selectMSG", map);
    }

    public List<MsgDto> listMsg(int memberCode, String msgCheck) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("msgCheck", msgCheck);
        return sqlSessionTemplate.selectList("dao.dellunaMapper.listMsg", map);
    }

    public int deleteAllMsg(int memberCode) {

        return sqlSessionTemplate.delete("dao.dellunaMapper.deleteAllMsg", memberCode);
    }

    public int deleteMsg(int memberCode, int msgCode) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("msgCode", msgCode);
        return sqlSessionTemplate.delete("dao.dellunaMapper.deleteMsg", map);
    }

    public int allMsg(int memberCode) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.allMsg", memberCode);
    }

    public int updateMsg(int memberCode, int msgCode, String msgCheck) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("msgCode", msgCode);
        map.put("msgCheck", msgCheck);
        return sqlSessionTemplate.update("dao.dellunaMapper.updateMsg", map);
    }

    public List<MsgDto> allMsgDto(int memberCode) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.allMsgDto", memberCode);
    }

    public int updateHouseReview(int memberCode, int reserveCode, String revContent) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("reserveCode", reserveCode);
        map.put("revContent", revContent);
        return sqlSessionTemplate.update("dao.dellunaMapper.updateHouseReview", map);
    }


    public List<ExReviewListDto> getExReviewListScroll(int memberCode, int startRow, int endRow) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        return sqlSessionTemplate.selectList("host.dao.mapper.getExReviewListScroll", map);
    }

    public List<MyHouseReviewList> getMyHouseReviewListScroll(int memberCode, int startRow, int endRow) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        return sqlSessionTemplate.selectList("host.dao.mapper.getMyHouseReviewListScroll", map);
    }

    public List<MyExReviewList> getMyExReviewListScroll(int memberCode, int startRow, int endRow) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        return sqlSessionTemplate.selectList("host.dao.mapper.getMyExReviewListScroll", map);
    }

    public List<NewExpReviewDto> myExpreviewList(int memberCode, int startRow, int endRow) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        return sqlSessionTemplate.selectList("dao.dellunaMapper.myExpreviewList", map);
    }

    public List<NewHouseReviewDto> myHousereviewList(int memberCode, int startRow, int endRow) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        return sqlSessionTemplate.selectList("dao.dellunaMapper.myHousereviewList", map);

    }

    public List<NewExpReserveDto> newExpReserve(int memberCode, String state) {

        Map<String, Object> map = new HashMap<>();

        map.put("memberCode", memberCode);
        map.put("state", state);
        return sqlSessionTemplate.selectList("dao.dellunaMapper.newExpReserve", map);
    }

    public List<NewHouseReserveDto> newHouseReserve(int memberCode, String state) {

        Map<String, Object> map = new HashMap<>();

        map.put("memberCode", memberCode);
        map.put("state", state);
        return sqlSessionTemplate.selectList("dao.dellunaMapper.newHouseReserve", map);
    }

    public List<NewExpReserveDto> newNewExpReserve(int memberCode) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.newNewExpReserve", memberCode);
    }

    public List<NewHouseReserveDto> newNewHouseReserve(int memberCode) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.newNewHouseReserve", memberCode);
    }


    public List<PointAccumulate> allAccuPoint(int memberCode) {

        return sqlSessionTemplate.selectList("dao.dellunaMapper.newPointAccu", memberCode);
    }

    public List<NewExpZzimDto> newExpZzimDto(int memberCode, int startRow, int endRow) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("startRow", startRow);
        map.put("endRow", endRow);

        return sqlSessionTemplate.selectList("dao.dellunaMapper.newExpZzimDto", map);
    }

    public List<NewHouseZzimDto> newHouseZzimDto(int memberCode, int startRow, int endRow) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("startRow", startRow);
        map.put("endRow", endRow);

        return sqlSessionTemplate.selectList("dao.dellunaMapper.newHouseZzimDto", map);
    }

    public int countExpZzim(int memberCode) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.countExpZzim", memberCode);
    }

    public int countHouseZzim(int memberCode) {

        return sqlSessionTemplate.selectOne("dao.dellunaMapper.countHouseZzim", memberCode);
    }

    public int countPayExp(int memberCode, String state) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("state", state);
        return sqlSessionTemplate.selectOne("dao.dellunaMapper.countPayExp", map);
    }

    public int countPayHouse(int memberCode, String state) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("state", state);
        return sqlSessionTemplate.selectOne("dao.dellunaMapper.countPayHouse", map);
    }

    public List<NewExpResDto> newExpResDto(int memberCode, String state, int startRow, int endRow) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("state", state);
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        return sqlSessionTemplate.selectList("dao.dellunaMapper.newExpResDto", map);
    }

    public List<NewHouseResDto> newHouseResDto(int memberCode, String state, int startRow, int endRow) {

        Map<String, Object> map = new HashMap<>();
        map.put("memberCode", memberCode);
        map.put("state", state);
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        return sqlSessionTemplate.selectList("dao.dellunaMapper.newHouseResDto", map);
    }

    public int getHouseReviewCount(int memberCode) {
        return sqlSessionTemplate.selectOne("host.dao.mapper.getHouseReviewCount", memberCode);
    }

    public int getExReviewCount(int memberCode) {
        return sqlSessionTemplate.selectOne("host.dao.mapper.getExReviewCount", memberCode);
    }

    public MemberDto selectMemberDto(int memberCode) {
        return sqlSessionTemplate.selectOne("dao.dellunaMapper.selectMemberDto", memberCode);

    }

    public int doExZzim(Map<String, Object> dataMap, String zzim) {
        if (zzim != null)
            return sqlSessionTemplate.insert("dao.dellunaMapper.doExZzim", dataMap);
        else
            return sqlSessionTemplate.delete("dao.dellunaMapper.cancelExZzim", dataMap);

    }

}
